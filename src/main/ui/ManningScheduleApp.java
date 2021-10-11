package ui;

public class ManningScheduleApp {

    public ManningScheduleApp() {
        menu();
    }


    private void menu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t Press 1 to go to the employee menu.");
        System.out.println("\t Press 2 to go to the positions menu.");
        System.out.println("\t Press 3 to exit.");
    }

    private void employeeMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t Press 1 to add an employee.");
        System.out.println("\t Press 2 to remove an employee.");
        System.out.println("\t Press 2 to add a skill to an employee.");
        System.out.println("\t Press 3 to show all employees.");
        System.out.println("\t Press 4 to return to main menu.");
    }

    private void positionMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t Press 1 to add a position.");
        System.out.println("\t Press 2 to remove a position.");
        System.out.println("\t Press 3 to show all positions.");
        System.out.println("\t Press 4 to return to main menu.");
    }

}
