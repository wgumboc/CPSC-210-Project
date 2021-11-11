package ui;

import model.*;
import persistence.MSJsonReader;
import persistence.MSJsonWriter;

import javax.swing.*;
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
    private int inputNum;
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

    // EFFECTS: initializes lists and brings up main menu
    public ManningScheduleAppGUI() {
        msJsonReader = new MSJsonReader(JSON_STORE);
        msJsonWriter = new MSJsonWriter(JSON_WRITE_STORE);
        createLists();
        createFrame();
        //menu();
    }

    // EFFECTS: Creates a frame.
    public void createFrame() {
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
    public JPanel employeePanel() {
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
    public JPanel positionPanel() {
        JPanel positionPanel = new JPanel();
        positionPanel.setBackground(new Color(209, 207, 226));
        positionPanel.setBounds(400,0,800,700);
        positionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        addPositionButtons(positionPanel, c);

        posNames = new DefaultListModel();

        posList = new JList(posNames);
        posList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        posList.setSelectedIndex(0);
        posList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(posList);

        listScroller.setPreferredSize(new Dimension(450, 400));
        c.gridwidth = 2;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 3;
        positionPanel.add(listScroller, c);

        return positionPanel;
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
        addPosition.setPreferredSize(new Dimension(225, 30));
        c.gridx = 0;
        c.gridy = 0;
        p.add(addPosition, c);

        removePosition = new JButton();
        removePosition.setText("Remove Position");
        removePosition.addActionListener(this);
        removePosition.setPreferredSize(new Dimension(225, 30));
        c.gridx = 1;
        c.gridy = 0;
        p.add(removePosition, c);
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
    // EFFECTS: displays menu items to the user and prompts for input
    private void menu() {
        Scanner userSelection = new Scanner(System.in);

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to go to the employee menu.");
        System.out.println("\t Press 2 to go to the positions menu.");
        System.out.println("\t Press 3 to load schedule from file.");
        System.out.println("\t Press 4 to save schedule to file.");
        System.out.println("\t Press 5 to exit.");

        menuSelection(inputNum);
    }


    // MODIFIES: this
    // EFFECTS: directs user to correct menu depending on user input
    private void menuSelection(int selection) {
        if (selection == 1) {
            //stub
        } else if (selection == 2) {
            //positionMenu();
        } else if (selection == 3) {
            loadSchedule();
        } else if (selection == 4) {
            saveSchedule();
        } else if (selection == 5) {
            System.out.println("Goodbye");
        } else {
            System.out.println("Not a valid selection.");
            menu();
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks user to enter employee name and creates Employee and puts into EmployeeRoster
    protected void addEmployee(String employeeName) {
        if (employeeName != null) {
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

            //correctIndex(index);
        }
    }

//    // templated from ListDemo on Oracle Java Swing Tutorials
//    // EFFECTS: Corrects the index of the JList.
//    private void correctIndex(int index) {
//        int size = eeNames.getSize();
//        if (size == 0) {
//            removeEmployee.setEnabled(false);
//        } else if (index == eeNames.getSize()) {
//            index--;
//        }
//
//        eeList.setSelectedIndex(index);
//        eeList.ensureIndexIsVisible(index);
//    }

    // REQUIRES: roster is not empty
    // EFFECTS: Displays all employees in the roster.
    private void showAllEmployees() {
        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            System.out.println(employee.getEmployeeName());
        }
    }

//    // MODIFIES: this
//    // EFFECTS: Adds a skill to an employee based on set skills.
//    private void addSkill(Employee employee) {
//        SkillsList employeeSkills;
//        Skill skill;
//        Scanner userSelection = new Scanner(System.in);
//
//        System.out.println("Please select a skill to add to an employee");
//        skillsListDisplay(qcDepSkills);
//
//        inputNum = selectionScanning(userSelection);
//
//        skill = qcDepSkills.getSkill(inputNum);
//        employeeSkills = employee.getSkills();
//
//        if (employeeSkills.hasSkill(skill)) {
//            System.out.println("Employee already has selected skill.");
//        } else {
//            employeeSkills.addSkill(skill);
//
//            System.out.println(skill.getSkillName() + " attributed to " + employee.getEmployeeName());
//            displaySkills(employee);
//        }
//    }
//
//    // REQUIRES: SkillList is not empty
//    // MODIFIES: this
//    // EFFECTS: Removes a specific skill of the employee
//    private void removeSkill(Employee employee) {
//        Scanner skillNum = new Scanner(System.in);
//        Skill skillToRemove;
//        SkillsList employeeSkills;
//        employeeSkills = employee.getSkills();
//
//        System.out.println("Here are all the skills of " + employee.getEmployeeName());
//
//        for (int i = 0; i < employeeSkills.skillsListSize(); i++) {
//            Skill getSkill = employeeSkills.getSkill(i);
//            System.out.println(i + " - " + getSkill.getSkillName());
//        }
//
//        System.out.println("Enter the number of the skill you would like to remove.");
//
//        inputNum = selectionScanning(skillNum);
//
//        if (inputNum < employeeSkills.skillsListSize()) {
//            skillToRemove = employeeSkills.getSkill(inputNum);
//            employeeSkills.removeSkill(skillToRemove);
//            System.out.println("Removed " + skillToRemove.getSkillName() + " from " + employee.getEmployeeName());
//        } else {
//            System.out.println("Not a valid selection");
//        }
//
//    }

    // REQUIRES: SkillList is not empty
    // EFFECTS: Displays all the employee's skills
    private void displaySkills(Employee employee) {
        SkillsList skillsList;
        Skill skill;
        skillsList = employee.getSkills();

        System.out.println(employee.getEmployeeName() + " has the following skills:");

        for (int i = 0; i < skillsList.skillsListSize(); i++) {
            skill = skillsList.getSkill(i);
            System.out.println(skill.getSkillName());
        }
    }


    // MODIFIES: this
    // EFFECTS: Asks user to enter a position name and creates Position and puts into list of positions
    private void addPosition() {
        Scanner input = new Scanner(System.in);
        Skill skill;
        System.out.println("Please enter the name of the Position you would like to add.");

        String positionName = input.nextLine();

        System.out.println("Please select the skill that you would like this position to require.");
        
//        skillsListDisplay(qcDepSkills);


        skill = qcDepSkills.getSkill(inputNum);

        Position position = new Position(positionName, skill);
        positionList.addPosition(position);

        System.out.println("Added " + positionName + " to list of positions.");

    }

    // REQUIRES: positionList is not empty
    // MODIFIES: this
    // EFFECTS: Removes a position from the list of positions.
    private void removePosition() {
        Scanner positionNum = new Scanner(System.in);
        Position posToRemove;

        System.out.println("Here are all the positions for the department:");

        for (int i = 0; i < positionList.positionListSize(); i++) {
            Position position = positionList.getPosition(i);
            System.out.println(i + " - " + position.getPositionName());
        }

        System.out.println("Enter the number of the position you would like to remove.");


        if (inputNum < positionList.positionListSize()) {
            posToRemove = positionList.getPosition(inputNum);
            positionList.removePosition(posToRemove);
            System.out.println("Removed " + posToRemove.getPositionName() + " from the list.");
        } else {
            System.out.println("Not a valid selection");
        }

    }

    // REQUIRES: positionList is not empty
    // EFFECTS: Displays all positions in the department.
    private void showAllPositions() {
        Position position;
        Employee positionEmployee;

        for (int i = 0; i < positionList.positionListSize(); i++) {
            position = positionList.getPosition(i);
            positionEmployee = position.getPositionEmployee();
            if (position.getPositionEmployee() == null) {
                System.out.println(position.getPositionName() + " - not filled");
            } else {
                System.out.println(position.getPositionName() + " filled by " + positionEmployee.getEmployeeName());
            }
        }
    }

    // REQUIRES: positionList not empty
    // MODIFIES: this
    // EFFECTS: Displays a menu with actions to act on a position, and prompts user for input.
    private void selectPosition() {
        Scanner userSelection = new Scanner(System.in);
        Position position;

        System.out.println("Please select a position.");

        for (int i = 0; i < positionList.positionListSize(); i++) {
            position = positionList.getPosition(i);
            System.out.println(i + " - " + position.getPositionName());
        }

        position = positionList.getPosition(inputNum);
        System.out.println("You have selected " + position.getPositionName());

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to assign an employee.");
        System.out.println("\t Press 2 to remove an assigned employee.");
        System.out.println("\t Press 3 to return to position menu.");


        positionSelection(inputNum, position);
    }

    // MODIFIES: this
    // EFFECTS: Directs the user to the appropriate action for the position depending on user input.
    private void positionSelection(int selection, Position position) {
        if (selection == 1) {
            fillPosition(position);
        } else if (selection == 2) {
            removeAssignment(position);
        } else if (selection == 3) {
            //
        } else {
            System.out.println("Not a valid selection.");
            selectPosition();
        }
    }

    // MODIFIES: this
    // EFFECTS: Gets the employee who the user wants to fill the position.
    private void fillPosition(Position position) {
        Scanner userSelection = new Scanner(System.in);
        Employee employee;

        System.out.println("Which employee would you like to assign to this position?");

        for (int i = 0; i < roster.rosterSize(); i++) {
            employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

        employee = roster.getEmployee(inputNum);

        assignPosition(position, employee);
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
            JOptionPane.showMessageDialog(null, "Schedule Loaded Successfully", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
