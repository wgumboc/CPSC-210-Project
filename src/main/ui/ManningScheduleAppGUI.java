package ui;

import model.*;
import persistence.MSJsonReader;
import persistence.MSJsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ManningScheduleAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/scheduleData.json";
    private static final String JSON_WRITE_STORE = "./data/scheduleData.json";
    private Schedule schedule;
    private EmployeeRoster roster;
    private PositionList positionList;
    private SkillsList qcDepSkills;
    private int inputNum;
    private MSJsonReader msJsonReader;
    private MSJsonWriter msJsonWriter;

    public static void createFrame() {
        JButton button = new JButton();
        button.setBounds(30, 30, 150, 50);
        button.setText("Add Employee");

        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(1200,700);
        frame.setVisible(true);

        frame.getContentPane().setBackground(new Color(171, 219, 227));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(223, 239, 239));
        panel.setBounds(0,0,400,700);
        frame.add(panel);
        frame.add(button);

        String employee = JOptionPane.showInputDialog("Enter the full name of the employee you wish to add:");
    }

    // EFFECTS: initializes lists and brings up main menu
    public ManningScheduleAppGUI() {
        msJsonReader = new MSJsonReader(JSON_STORE);
        msJsonWriter = new MSJsonWriter(JSON_WRITE_STORE);
        createLists();
        createFrame();
        menu();
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

        inputNum = selectionScanning(userSelection);

        menuSelection(inputNum);
    }

    // templated from https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
    // MODIFIES: this
    // EFFECTS: scans for user input and rejects non-integers
    private int selectionScanning(Scanner scanner) {
        int num;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            num = scanner.nextInt();
            if (num < 0) {
                System.out.println("Please enter a non-negative integer.");
            }
        } while (num < 0);
        return num;
    }

    // MODIFIES: this
    // EFFECTS: directs user to correct menu depending on user input
    private void menuSelection(int selection) {
        if (selection == 1) {
            employeeMenu();
        } else if (selection == 2) {
            positionMenu();
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
    // EFFECTS: gives user options for employee, and prompts user for input
    private void employeeMenu() {
        Scanner userSelection = new Scanner(System.in);

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to add an employee.");
        System.out.println("\t Press 2 to remove an employee.");
        System.out.println("\t Press 3 to show all employees.");
        System.out.println("\t Press 4 to select an employee.");
        System.out.println("\t Press 5 to return to main menu.");

        inputNum = selectionScanning(userSelection);

        employeeMenuSelection(inputNum);
    }

    // MODIFIES: this
    // EFFECTS: directs user to correct employee operation
    private void employeeMenuSelection(int selection) {
        if (selection == 1) {
            addEmployee();
        } else if (selection == 2) {
            removeEmployee();
        } else if (selection == 3) {
            showAllEmployees();
        } else if (selection == 4) {
            selectEmployee();
        } else if (selection == 5) {
            menu();
        } else {
            System.out.println("Not a valid selection.");
            menu();
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks user to enter employee name and creates Employee and puts into EmployeeRoster
    private void addEmployee() {
        Scanner nameInput = new Scanner(System.in);
        System.out.println("Please enter the name of the employee you would like to add.");

        String employeeName = nameInput.nextLine();

        Employee employee = new Employee(employeeName);
        roster.addEmployee(employee);

        System.out.println("Added " + employeeName + " to Employee Roster");

        employeeMenu();
    }

    // REQUIRES: roster is not empty
    // MODIFIES: this
    // EFFECTS: Removes an employee, specified by the user, from the roster
    private void removeEmployee() {
        Scanner employeeNum = new Scanner(System.in);
        Employee eeToRemove;

        System.out.println("Here are all the employees on the roster.");

        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

        System.out.println("Enter the number of the employee you would like to remove.");

        inputNum = selectionScanning(employeeNum);

        if (inputNum < roster.rosterSize()) {
            eeToRemove = roster.getEmployee(inputNum);
            roster.removeEmployee(eeToRemove);
            System.out.println("Removed " + eeToRemove.getEmployeeName() + " from the roster.");
        } else {
            System.out.println("Not a valid selection");
        }

        employeeMenu();
    }

    // REQUIRES: roster is not empty
    // EFFECTS: Displays all employees in the roster.
    private void showAllEmployees() {
        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            System.out.println(employee.getEmployeeName());
        }
        employeeMenu();
    }

    // REQUIRES: roster is not empty
    // MODIFIES: this
    // EFFECTS: Displays a menu with actions to act on an employee, and prompts user for input.
    private void selectEmployee() {
        Scanner userSelection = new Scanner(System.in);
        Employee employee;

        System.out.println("Please select an employee.");

        for (int i = 0; i < roster.rosterSize(); i++) {
            employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

        inputNum = selectionScanning(userSelection);

        employee = roster.getEmployee(inputNum);
        System.out.println("You have selected " + employee.getEmployeeName());

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to add a skill.");
        System.out.println("\t Press 2 to remove a skill.");
        System.out.println("\t Press 3 to display all skills.");
        System.out.println("\t Press 4 to return to employee menu.");

        inputNum = selectionScanning(userSelection);

        employeeSelection(inputNum, employee);
    }

    // MODIFIES: this
    // EFFECTS: Directs the user to the appropriate action for the employee depending on user input.
    private void employeeSelection(int selection, Employee employee) {
        if (selection == 1) {
            addSkill(employee);
        } else if (selection == 2) {
            removeSkill(employee);
        } else if (selection == 3) {
            displaySkills(employee);
        } else if (selection == 4) {
            employeeMenu();
        } else {
            System.out.println("Not a valid selection.");
            selectEmployee();
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a skill to an employee based on set skills.
    private void addSkill(Employee employee) {
        SkillsList employeeSkills;
        Skill skill;
        Scanner userSelection = new Scanner(System.in);

        System.out.println("Please select a skill to add to an employee");
        skillsListDisplay(qcDepSkills);

        inputNum = selectionScanning(userSelection);

        skill = qcDepSkills.getSkill(inputNum);
        employeeSkills = employee.getSkills();

        if (employeeSkills.hasSkill(skill)) {
            System.out.println("Employee already has selected skill.");
            employeeMenu();
        } else {
            employeeSkills.addSkill(skill);

            System.out.println(skill.getSkillName() + " attributed to " + employee.getEmployeeName());
            displaySkills(employee);
        }
    }

    // REQUIRES: SkillList is not empty
    // MODIFIES: this
    // EFFECTS: Removes a specific skill of the employee
    private void removeSkill(Employee employee) {
        Scanner skillNum = new Scanner(System.in);
        Skill skillToRemove;
        SkillsList employeeSkills;
        employeeSkills = employee.getSkills();

        System.out.println("Here are all the skills of " + employee.getEmployeeName());

        for (int i = 0; i < employeeSkills.skillsListSize(); i++) {
            Skill getSkill = employeeSkills.getSkill(i);
            System.out.println(i + " - " + getSkill.getSkillName());
        }

        System.out.println("Enter the number of the skill you would like to remove.");

        inputNum = selectionScanning(skillNum);

        if (inputNum < employeeSkills.skillsListSize()) {
            skillToRemove = employeeSkills.getSkill(inputNum);
            employeeSkills.removeSkill(skillToRemove);
            System.out.println("Removed " + skillToRemove.getSkillName() + " from " + employee.getEmployeeName());
        } else {
            System.out.println("Not a valid selection");
        }

        employeeMenu();
    }

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

        employeeMenu();
    }

    // REQUIRES: SkillList is not empty
    // EFFECTS: displays all skills in the skillsList
    private void skillsListDisplay(SkillsList skillsList) {
        for (int i = 0; i < skillsList.skillsListSize(); i++) {
            Skill getSkill = skillsList.getSkill(i);
            System.out.println(i + " - " + getSkill.getSkillName());
        }
    }

    // MODIFIES: this
    // EFFECTS: gives user options to add/remove/show all/ or select positions and prompts user for input
    private void positionMenu() {
        Scanner userSelection = new Scanner(System.in);

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to add a position.");
        System.out.println("\t Press 2 to remove a position.");
        System.out.println("\t Press 3 to show all positions.");
        System.out.println("\t Press 4 to select a position.");
        System.out.println("\t Press 5 to return to main menu.");

        inputNum = selectionScanning(userSelection);

        positionMenuSelection(inputNum);
    }

    // MODIFIES: this
    // EFFECTS: directs user to correct position operation depending on user input
    private void positionMenuSelection(int selection) {
        if (selection == 1) {
            addPosition();
        } else if (selection == 2) {
            removePosition();
        } else if (selection == 3) {
            showAllPositions();
        } else if (selection == 4) {
            selectPosition();
        } else if (selection == 5) {
            menu();
        } else {
            System.out.println("Not a valid selection.");
            menu();
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
        
        skillsListDisplay(qcDepSkills);

        inputNum = selectionScanning(input);
        skill = qcDepSkills.getSkill(inputNum);

        Position position = new Position(positionName, skill);
        positionList.addPosition(position);

        System.out.println("Added " + positionName + " to list of positions.");

        positionMenu();
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

        inputNum = selectionScanning(positionNum);

        if (inputNum < positionList.positionListSize()) {
            posToRemove = positionList.getPosition(inputNum);
            positionList.removePosition(posToRemove);
            System.out.println("Removed " + posToRemove.getPositionName() + " from the list.");
        } else {
            System.out.println("Not a valid selection");
        }

        positionMenu();
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
        positionMenu();
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

        inputNum = selectionScanning(userSelection);
        position = positionList.getPosition(inputNum);
        System.out.println("You have selected " + position.getPositionName());

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to assign an employee.");
        System.out.println("\t Press 2 to remove an assigned employee.");
        System.out.println("\t Press 3 to return to position menu.");

        inputNum = selectionScanning(userSelection);

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
            positionMenu();
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

        inputNum = selectionScanning(userSelection);
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

        positionMenu();
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

        positionMenu();
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
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_WRITE_STORE);
        }
        menu();
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
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        menu();
    }

}
