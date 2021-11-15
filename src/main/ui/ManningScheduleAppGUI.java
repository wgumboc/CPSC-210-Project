package ui;

import model.*;
import persistence.MSJsonReader;
import persistence.MSJsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Templated lists from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
public class ManningScheduleAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/scheduleData.json";
    private static final String JSON_WRITE_STORE = "./data/scheduleData.json";
    private Schedule schedule;
    private EmployeeRoster roster;
    private PositionList positionList;
    private SkillsList qcDepSkills;
    private MSJsonReader msJsonReader;
    private MSJsonWriter msJsonWriter;
    private JButton addEmployee;
    private JButton removeEmployee;
    private JButton employeeSkills;
    private JButton addPosition;
    private JButton removePosition;
    private JButton saveSchedule;
    private JButton loadSchedule;
    private JFrame frame;
    private JList eeList;
    private JList posList;
    private DefaultListModel eeNames;
    private DefaultListModel posNames;
    private JTable table;
    private Object[][] positionData;

    // EFFECTS: initializes lists and brings up main menu
    public ManningScheduleAppGUI() {
        msJsonReader = new MSJsonReader(JSON_STORE);
        msJsonWriter = new MSJsonWriter(JSON_WRITE_STORE);
        createLists();
        createFrame();
        //menu();
    }

    // EFFECTS: Creates a frame.
    private void createFrame() {
        frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(1200,700);

        frame.getContentPane().setBackground(new Color(171, 219, 227));

        frame.add(employeePanel());
        frame.add(positionPanel());
        frame.setVisible(true);
    }

    // EFFECTS: Constructs a panel to contain employee functions
    private JPanel employeePanel() {
        JPanel employeePanel = new JPanel();
        employeePanel.setBackground(new Color(223, 239, 239));
        employeePanel.setBounds(0,0,400,700);
        employeePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        addEmployeeButtons(employeePanel, c);
        addSaveLoad(employeePanel, c);

        eeNames = new DefaultListModel();

        eeList = new JList(eeNames); //data has type Object[]
        eeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eeList.setSelectedIndex(0);
        eeList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(eeList);
        listScroller.setPreferredSize(new Dimension(250, 400));
        c.gridwidth = 2;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 3;
        employeePanel.add(listScroller, c);

        return employeePanel;
    }

    // EFFECTS: Constructs a panel to contain employee functions
    private JPanel positionPanel() {
        JPanel positionPanel = new JPanel();
        positionPanel.setBackground(new Color(209, 207, 226));
        positionPanel.setBounds(400,0,800,700);
        positionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        addPositionButtons(positionPanel, c);
        addTable(positionPanel, c);

        return positionPanel;
    }

    private Object[][] positionsData() {
        positionData = new Object[23][3];

        return positionData;
    }

    // MODIFIES: this
    // EFFECTS: Creates a panel with buttons to add/remove employees and add/remove skills to each
    private void addEmployeeButtons(JPanel p, GridBagConstraints c) {
        addEmployee = new JButton();
        addEmployee.setText("Add Employee");
        addEmployee.addActionListener(this);
        addEmployee.setPreferredSize(new Dimension(140, 30));
        c.gridx = 0;
        c.gridy = 0;
        p.add(addEmployee, c);

        removeEmployee = new JButton();
        removeEmployee.setText("Remove Employee");
        removeEmployee.addActionListener(this);
        removeEmployee.setPreferredSize(new Dimension(140, 30));
        c.gridx = 1;
        c.gridy = 0;
        p.add(removeEmployee, c);

        employeeSkills = new JButton();
        employeeSkills.setText("Add/Remove Skills");
        employeeSkills.addActionListener(this);
        employeeSkills.setPreferredSize(new Dimension(280, 30));
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        p.add(employeeSkills, c);
    }

    // MODIFIES: this
    // EFFECTS: Creates a panel with buttons to add/remove employees and add/remove skills to each
    private void addPositionButtons(JPanel p, GridBagConstraints c) {
        addPosition = new JButton();
        addPosition.setText("Add Position");
        addPosition.addActionListener(this);
        addPosition.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 0;
        p.add(addPosition, c);

        removePosition = new JButton();
        removePosition.setText("Remove Position");
        removePosition.addActionListener(this);
        removePosition.setPreferredSize(new Dimension(200, 30));
        c.gridx = 1;
        c.gridy = 0;
        p.add(removePosition, c);
    }

    // templated from TableSelectionDemo from oracle
    // MODIFIES: this
    // EFFECTS: Creates a table to display position properties.
    private void addTable(JPanel p, GridBagConstraints c) {
        table = new JTable(new DefaultTableModel(new Object[]{"Position", "Employee", "Required Skill"},0)) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setPreferredSize(new Dimension(140, 30));
        table.setFillsViewportHeight(true);
        table.setVisible(true);
        table.removeEditor();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(400, 400));
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        p.add(jsp, c);
    }

    private void addSaveLoad(JPanel p, GridBagConstraints c) {
        saveSchedule = new JButton();
        saveSchedule.setText("Save");
        saveSchedule.addActionListener(this);
        saveSchedule.setPreferredSize(new Dimension(140, 30));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        p.add(saveSchedule, c);

        loadSchedule = new JButton();
        loadSchedule.setText("Load");
        loadSchedule.addActionListener(this);
        loadSchedule.setPreferredSize(new Dimension(140, 30));
        c.gridx = 1;
        c.gridy = 2;
        p.add(loadSchedule, c);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEmployee) {
            String employeeName = JOptionPane.showInputDialog("Enter the full name of the employee you wish to add:");
            addEmployee(employeeName);
        }

        if (e.getSource() == removeEmployee) {
            removeEmployee();
        }

        if (e.getSource() == employeeSkills) {
            int index = eeList.getSelectedIndex();
            if (index != -1) {
                Employee selectedEmployee = getSelectedEmployee();
                SkillsWindow window = new SkillsWindow(selectedEmployee);
            }
        }

        if (e.getSource() == addPosition) {
            addPosition();
        }

        if (e.getSource() == removePosition) {
            removePosition();
        }

        if (e.getSource() == saveSchedule) {
            saveSchedule();
        }

        if (e.getSource() == loadSchedule) {
            loadSchedule();
        }
    }

    private Employee getSelectedEmployee() {
        Employee selected = null;

        String eeName = eeList.getSelectedValue().toString();

        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            String name = employee.getEmployeeName();
            if (name.equals(eeName)) {
                selected = employee;
            }
        }

        return selected;
    }

    // MODIFIES: this
    // EFFECTS: creates position, roster, and skills lists
    private void createLists() {
        schedule = new Schedule();
        roster = schedule.getRoster();
        positionList = schedule.getPositionList();
        qcDepSkills = schedule.getQCSkillsList();
    }


    // MODIFIES: this
    // EFFECTS: Asks user to enter employee name and creates Employee and puts into EmployeeRoster
    private void addEmployee(String employeeName) {
        if (!employeeName.equals("")) {
            Employee employee = new Employee(employeeName);
            roster.addEmployee(employee);

            addToJListData();

            JOptionPane.showMessageDialog(null, "Added " + employeeName + " to roster.", "",
                    JOptionPane.INFORMATION_MESSAGE);

            removeEmployee.setEnabled(true);
        }
    }

    private void addToJListData() {
        for (Employee e: roster.getRoster()) {
            String name = e.getEmployeeName();
            if (!eeNames.contains(name)) {
                eeNames.addElement(name);
            }
        }
    }

    // REQUIRES: roster is not empty
    // MODIFIES: this
    // EFFECTS: Removes an employee, specified by the user, from the roster
    private void removeEmployee() {
        Employee eeToRemove;

        int index = eeList.getSelectedIndex();
        if (index != -1) {
            String removeName = eeList.getSelectedValue().toString();

            for (int i = 0; i < roster.rosterSize(); i++) {
                Employee employee = roster.getEmployee(i);
                String name = employee.getEmployeeName();
                if (name.equals(removeName)) {
                    eeToRemove = employee;
                    roster.removeEmployee(eeToRemove);
                }
            }

            eeNames.remove(index);

            JOptionPane.showMessageDialog(null, "Removed " + removeName + " from roster.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks user to enter a position name and creates Position and puts into list of positions
    private void addPosition() {
        Skill skill = null;
        String positionName = JOptionPane.showInputDialog("Enter the name of the position you wish to add:");

        if (!positionName.equals("")) {
            Position position = new Position(positionName, skill);

            SingleSkillSelection s = new SingleSkillSelection(position, positionList, table);

            positionList.addPosition(position);
        }
    }

    // REQUIRES: positionList is not empty
    // MODIFIES: this
    // EFFECTS: Removes a position from the list of positions.
    private void removePosition() {
        Position posToRemove;


        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            String removeName = table.getModel().getValueAt(selectedRow, 0).toString();

            for (int i = 0; i < positionList.positionListSize(); i++) {
                Position position = positionList.getPosition(i);
                String name = position.getPositionName();
                if (name.equals(removeName)) {
                    posToRemove = position;
                    positionList.removePosition(posToRemove);
                }
            }
        }

        removeSelectedRows(table);

    }

    // Taken from https://stackoverflow.com/questions/655325/how-do-you-remove-selected-rows-from-a-jtable
    public void removeSelectedRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        for (int i = 0; i < rows.length;i++) {
            model.removeRow(rows[i] - i);
        }
    }

    // MODIFIES: this
    // EFFECTS: Gets the employee who the user wants to fill the position.
    private void fillPosition(Position position) {
        Employee employee;

        System.out.println("Which employee would you like to assign to this position?");

        for (int i = 0; i < roster.rosterSize(); i++) {
            employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

//        employee = roster.getEmployee(inputNum);

//        assignPosition(position, employee);
    }

    // MODIFIES: this
    // EFFECTS: Fills the position with the employee if the position is open, the employee does not have a
    //          current position, and the employee possess the correct skill.
    private void assignPosition(Position position, Employee employee) {
        SkillsList employeeSkills = employee.getSkills();
        Skill positionSkill = position.getPositionSkill();

        if (employee.hasPosition()) {
            System.out.println(employee.getEmployeeName() + " already has a position.");
        } else if (position.isFull()) {
            System.out.println(position.getPositionName() + " is already filled.");
        } else if (!(employeeSkills.hasSkill(positionSkill))) {
            System.out.println(employee.getEmployeeName() + " does not have the right skills to fill this position.");
        } else {
            position.fillPosition(employee);
            System.out.println(employee.getEmployeeName() + " has been assigned to " + position.getPositionName());
        }

    }

    // MODIFIES: this
    // EFFECTS: Removes the assigned employee from the position if the position is not empty.
    private void removeAssignment(Position position) {
        Employee posEmployee = position.getPositionEmployee();
        if (!position.removeEmployee()) {
            System.out.println("No employee in position.");
        } else {
            position.removeEmployee();
            System.out.println(posEmployee.getEmployeeName() + " has been removed from " + position.getPositionName());
        }

    }

    // templated from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the workroom to file
    private void saveSchedule() {
        try {
            msJsonWriter.open();
            msJsonWriter.write(schedule);
            msJsonWriter.close();
            System.out.println("Saved to " + JSON_WRITE_STORE);
            JOptionPane.showMessageDialog(null, "Schedule Saved Successfully", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_WRITE_STORE);
        }
    }

    // templated from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSchedule() {
        try {
            schedule = msJsonReader.read();
            roster = schedule.getRoster();
            positionList = schedule.getPositionList();
            System.out.println("Loaded schedule from " + JSON_STORE);
            addToJListData();
            SingleSkillSelection s = new SingleSkillSelection(positionList, table);
            s.addToPositionData();

            JOptionPane.showMessageDialog(null, "Schedule Loaded Successfully", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    // MODIFIES: this
//    // EFFECTS: Adds the data of all positions to the table
//    private void addToPositionData() {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        for (Position p: positionList.getAllPositions()) {
//            String employeeName;
//            String positionName = p.getPositionName();
//            String skillName = p.getPositionSkill().getSkillName();
//            if (p.getPositionEmployee() != null) {
//                employeeName = p.getPositionEmployee().getEmployeeName();
//            } else {
//                employeeName = "None";
//            }
//
//            if (!existsInTable(table, new String[]{positionName, employeeName, skillName})) {
//                model.addRow(new String[]{positionName, employeeName, skillName});
//            }
//        }
//    }
//
//    // Taken from https://stackoverflow.com/questions/15639611/how-to-check-if-a-value-exists-
//    //            in-jtable-which-i-am-trying-to-add
//    // EFFECTS: checks if a value already exists in table
//    public boolean existsInTable(JTable table, Object[] entry) {
//
//        // Get row and column count
//        int rowCount = table.getRowCount();
//        int colCount = table.getColumnCount();
//
//        // Get Current Table Entry
//        String curEntry = "";
//        for (Object o : entry) {
//            String e = o.toString();
//            curEntry = curEntry + " " + e;
//        }
//
//        // Check against all entries
//        for (int i = 0; i < rowCount; i++) {
//            String rowEntry = "";
//            for (int j = 0; j < colCount; j++) {
//                rowEntry = rowEntry + " " + table.getValueAt(i, j).toString();
//            }
//            if (rowEntry.equalsIgnoreCase(curEntry)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
