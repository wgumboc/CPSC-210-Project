package ui;

import model.Employee;
import model.Skill;
import model.SkillsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillsWindow implements ActionListener {
    private JFrame frame;
    private JPanel skillsPanel;
    private JButton addSkill;
    private JButton removeSkill;
    private JList allSkillsList;
    private JList eeSkillsList;
    private DefaultListModel skillNames;
    private DefaultListModel eeSkillNames;
    private SkillsList allSkills;
    private Employee employee;


    public SkillsWindow(Employee selectedEmployee) {
        allSkills = new SkillsList();
        allSkills.qcSkillsList();
        employee = selectedEmployee;
        frame = new JFrame("Skills for: " + selectedEmployee.getEmployeeName());
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(400,400);

        frame.getContentPane().setBackground(new Color(171, 219, 227));

        frame.add(skillsPanel());

        frame.setVisible(true);
    }

    // EFFECTS: Constructs a panel to contain employee functions
    public JPanel skillsPanel() {
        skillsPanel = new JPanel();
        skillsPanel.setBackground(new Color(223, 239, 239));
        skillsPanel.setSize(400,400);
        skillsPanel.setLayout(null);
        addButtons(skillsPanel);
        addLabels();
        addLists();

        return skillsPanel;
    }

    public void addButtons(JPanel p) {
        addSkill = new JButton();
        addSkill.setText("Add Skill");
        addSkill.addActionListener(this);
        addSkill.setBounds(40, 20, 140, 30);

        p.add(addSkill);

        removeSkill = new JButton();
        removeSkill.setText("Remove Skill");
        removeSkill.addActionListener(this);
        removeSkill.setBounds(205, 20, 140, 30);
        p.add(removeSkill);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSkill) {
            addSkill();
        }

        if (e.getSource() == removeSkill) {
            removeSkill();
        }

    }

    private void addLists() {
        skillNames = new DefaultListModel();

        allSkillsList = new JList(skillNames);
        allSkillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allSkillsList.setSelectedIndex(0);
        allSkillsList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(allSkillsList);
        listScroller.setBounds(35,80, 150, 270);
        skillsPanel.add(listScroller);

        eeSkillNames = new DefaultListModel();

        eeSkillsList = new JList(eeSkillNames);
        eeSkillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eeSkillsList.setSelectedIndex(0);
        eeSkillsList.setVisibleRowCount(5);
        JScrollPane eeScroller = new JScrollPane(eeSkillsList);
        eeScroller.setBounds(200,80, 150, 270);
        skillsPanel.add(eeScroller);

        addESkillsToData(employee.getSkills());
        addToJListData();
    }

    private void addLabels() {
        JLabel skillNamesLabel = new JLabel();
        skillNamesLabel.setText("Available Skills:");
        skillNamesLabel.setBounds(65,40, 100, 50);
        skillsPanel.add(skillNamesLabel);

        JLabel eeSkillsLabel = new JLabel();
        eeSkillsLabel.setText("Employee's Skills:");
        eeSkillsLabel.setBounds(230,40, 100, 50);
        skillsPanel.add(eeSkillsLabel);
    }

    private void addESkillsToData(SkillsList sl) {
        for (Skill s: sl.getList()) {
            String name = s.getSkillName();
            if (!eeSkillNames.contains(name)) {
                eeSkillNames.addElement(name);
            }
        }
    }

    private void addToJListData() {
        for (Skill s: allSkills.getList()) {
            String name = s.getSkillName();
            if (!skillNames.contains(name) && !eeSkillNames.contains(name)) {
                skillNames.addElement(name);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a skill to an employee based on set skills.
    private void addSkill() {
        Skill skill;
        int index = allSkillsList.getSelectedIndex();
        if (index != -1) {
            skill = getSelectedSkill();

            if (employee.getSkills().hasSkill(skill)) {
                JOptionPane.showMessageDialog(null, "Employee already has " + skill, "",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                employee.getSkills().addSkill(skill);

                System.out.println(skill.getSkillName() + " attributed to " + employee.getEmployeeName());
                eeSkillNames.addElement(skill.getSkillName());
                skillNames.remove(index);
            }
        }
    }


    // REQUIRES: SkillList is not empty
    // MODIFIES: this
    // EFFECTS: Removes a specific skill of the employee
    private void removeSkill() {
        SkillsList employeeSkills;
        employeeSkills = employee.getSkills();

        int index = eeSkillsList.getSelectedIndex();
        if (index != -1) {
            String removeName = eeSkillsList.getSelectedValue().toString();

            for (int i = 0; i < employeeSkills.skillsListSize(); i++) {
                Skill skill = employeeSkills.getSkill(i);
                String skillName = skill.getSkillName();
                if (skillName.equals(removeName)) {
                    employeeSkills.removeSkill(skill);
                }
            }

            eeSkillNames.remove(index);
            skillNames.addElement(removeName);

        }
    }

    private Skill getSelectedSkill() {
        Skill selected = null;

        String skillName = allSkillsList.getSelectedValue().toString();

        for (int i = 0; i < allSkills.skillsListSize(); i++) {
            Skill skill = allSkills.getSkill(i);
            String name = skill.getSkillName();
            if (name.equals(skillName)) {
                selected = skill;
            }
        }

        return selected;
    }

//

}
