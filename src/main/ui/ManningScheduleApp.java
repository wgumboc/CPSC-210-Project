package ui;

import model.Employee;
import model.EmployeeRoster;
import model.PositionList;

import java.util.Scanner;

public class ManningScheduleApp {
    private EmployeeRoster roster;
    private PositionList positionList;
    private int inputNum;

    public ManningScheduleApp() {
        menu();
    }

    // Templated from stack overflow
    // EFFECTS: lets user select menu
    private void menu() {
        Scanner userSelection = new Scanner(System.in);

        createLists();

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to go to the employee menu.");
        System.out.println("\t Press 2 to go to the positions menu.");
        System.out.println("\t Press 3 to exit.");

        inputNum = userSelection.nextInt();

        menuSelection(inputNum);
    }

    private void createLists() {
        roster = new EmployeeRoster();
        positionList = new PositionList();
    }

    // EFFECTS: directs user to correct menu
    private void menuSelection(int selection) {
        if (selection == 1) {
            employeeMenu();
        } else if (selection == 2) {
            positionMenu();
        } else if (selection == 3) {
            System.out.println("Goodbye");
        } else {
            System.out.println("Not a valid selection.");
            menu();
        }
    }

    // EFFECTS: gives user options for employee
    private void employeeMenu() {
        Scanner userSelection = new Scanner(System.in);

        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to add an employee.");
        System.out.println("\t Press 2 to remove an employee.");
        System.out.println("\t Press 3 to add a skill to an employee.");
        System.out.println("\t Press 4 to show all employees.");
        System.out.println("\t Press 5 to return to main menu.");

        inputNum = userSelection.nextInt();

        employeeSelection(inputNum);
    }

    // EFFECTS: directs user to correct employee operation
    private void employeeSelection(int selection) {
        if (selection == 1) {
            addEmployeeUI();
        } else if (selection == 2) {
            removeEmployeeUI();
        } else if (selection == 3) {
            addSkillEmployeeUI();
        } else if (selection == 4) {
            showAllEmployeesUI();
        } else if (selection == 5) {
            menu();
        } else {
            System.out.println("Not a valid selection.");
            menu();
        }
    }

    // EFFECTS: Asks user to enter employee name and creates Employee and puts into EmployeeRoster
    private void addEmployeeUI() {
        Scanner nameInput = new Scanner(System.in);
        System.out.println("Please enter the name of the employee you would like to add.");

        String employeeName = nameInput.nextLine();

        Employee employee = new Employee(employeeName);
        roster.addEmployee(employee);

        System.out.println("Added " + employeeName + " to Employee Roster");

        employeeMenu();
    }

    // EFFECTS: Removes an employee, specified by the user, from the roster
    private void removeEmployeeUI() {
        Scanner employeeNum = new Scanner(System.in);
        Employee eeToRemove;

        System.out.println("Here are all the employees on the roster.");

        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

        System.out.println("Enter the number of the employee you would like to remove.");

        inputNum = employeeNum.nextInt();

        if (inputNum < roster.rosterSize()) {
            eeToRemove = roster.getEmployee(inputNum);
            roster.removeEmployee(eeToRemove);
            System.out.println("Removed " + eeToRemove.getEmployeeName() + " from the roster.");
        } else {
            System.out.println("Not a valid selection");
        }

        employeeMenu();
    }

    // EFFECTS: Displays all employees in the roster.
    private void showAllEmployeesUI() {
        for (int i = 0; i < roster.rosterSize(); i++) {
            Employee employee = roster.getEmployee(i);
            System.out.println(employee.getEmployeeName());
        }
        employeeMenu();
    }

    // EFFECTS: Adds a skill to an employee based on set skills.
    private void addSkillEmployeeUI() {
        Scanner userSelection = new Scanner(System.in);
        Employee employee;

        System.out.println("Please select an employee.");

        for (int i = 0; i < roster.rosterSize(); i++) {
            employee = roster.getEmployee(i);
            System.out.println(i + " - " + employee.getEmployeeName());
        }

        inputNum = userSelection.nextInt();

        employee = roster.getEmployee(inputNum);

        System.out.println("Please select a skill to add to an employee");

        skillsList();

        employeeMenu();
    }

    private void skillsList() {
        employeeMenu();
    }

    // EFFECTS: gives user options for position
    private void positionMenu() {
        System.out.println("\n What would you like to do?");
        System.out.println("\t Press 1 to add a position.");
        System.out.println("\t Press 2 to remove a position.");
        System.out.println("\t Press 3 to show all positions.");
        System.out.println("\t Press 4 to return to main menu.");
    }

}
