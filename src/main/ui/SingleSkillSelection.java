package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleSkillSelection implements ActionListener {
    private JFrame frame;
    private JPanel skillsPanel;
    private JButton addSkill;
    private JList allSkillsList;
    private DefaultListModel skillNames;
    private SkillsList allSkills;
    private Skill skill;
    private Position position;
    private PositionList positionList;
    private JTable table;

    public SingleSkillSelection(PositionList positionList, JTable table) {
        this.positionList = positionList;
        this.table = table;
    }


    public SingleSkillSelection(Position position, PositionList positionList, JTable table) {
        this.position = position;
        this.positionList = positionList;
        this.table = table;
        allSkills = new SkillsList();
        allSkills.qcSkillsList();
        frame = new JFrame("Select a skill required for the position.");
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
        addSkill.setText("Confirm Skill");
        addSkill.addActionListener(this);
        addSkill.setBounds(40, 20, 140, 30);

        p.add(addSkill);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSkill) {
            addSkill();
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
        addToJListData();
    }

    private void addLabels() {
        JLabel skillNamesLabel = new JLabel();
        skillNamesLabel.setText("Available Skills:");
        skillNamesLabel.setBounds(65,40, 100, 50);
        skillsPanel.add(skillNamesLabel);
    }

    private void addToJListData() {
        for (Skill s: allSkills.getList()) {
            String name = s.getSkillName();
            if (!skillNames.contains(name)) {
                skillNames.addElement(name);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a skill to an employee based on set skills.
    private void addSkill() {
        int index = allSkillsList.getSelectedIndex();
        if (index != -1) {
            skill = getSelectedSkill();
        }
        position.assignSkill(skill);
        addToPositionData();
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

    // MODIFIES: this
    // EFFECTS: Adds the data of all positions to the table
    public void addToPositionData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Position p: positionList.getAllPositions()) {
            String positionName = p.getPositionName();
            String skillName = p.getPositionSkill().getSkillName();

            if (!existsInTable(table, new String[]{positionName, "employeeName", skillName})) {
                model.addRow(new String[]{positionName, "employeeName", skillName});
            }
        }
        if (frame != null) {
            frame.dispose();
        }
    }

    // Taken from https://stackoverflow.com/questions/15639611/how-to-check-if-a-value-exists-
    //            in-jtable-which-i-am-trying-to-add
    // EFFECTS: checks if a value already exists in table
    public boolean existsInTable(JTable table, Object[] entry) {

        // Get row and column count
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();

        // Get Current Table Entry
        String curEntry = "";
        for (Object o : entry) {
            String e = o.toString();
            curEntry = curEntry + " " + e;
        }

        // Check against all entries
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = "";
            for (int j = 0; j < colCount; j++) {
                rowEntry = rowEntry + " " + table.getValueAt(i, j).toString();
            }
            if (rowEntry.equalsIgnoreCase(curEntry)) {
                return true;
            }
        }
        return false;
    }

}
