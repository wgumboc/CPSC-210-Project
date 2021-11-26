package ui;

import model.Employee;
import model.Skill;
import model.SkillsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeSkillsWindow implements ActionListener {
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


    // EFFECTS: constructs the SkillsWindow object
    public EmployeeSkillsWindow(Employee selectedEmployee) {
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

    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: Adds the add/remove skills buttons to the panel
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

    // EFFECTS: listens for buttons to be pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSkill) {
            addSkill();
        }

        if (e.getSource() == removeSkill) {
            removeSkill();
        }

    }

    // MODIFIES: this
    // EFFECTS: adds the list of skills to the panel
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

    // MODIFIES: this
    // EFFECTS: adds the appropriate labels to the panel
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

    // MODIFIES: this
    // EFFECTS: add the skills that the employee possesses to the data
    private void addESkillsToData(SkillsList sl) {
        for (Skill s: sl.getList()) {
            String name = s.getSkillName();
            if (!eeSkillNames.contains(name)) {
                eeSkillNames.addElement(name);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add the skills that the employee does not posses to the data
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
                employee.getSkills().addSkill(skill, employee);

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
                    employeeSkills.removeSkill(skill, employee);
                }
            }

            eeSkillNames.remove(index);
            skillNames.addElement(removeName);

        }
    }

    // EFFECTS: returns the skill that the user has selected in the list
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
