package ui;

import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

class EmployeePanel extends JPanel implements ActionListener {
    private JButton button;

    public EmployeePanel() {
        JPanel employeePanel = new JPanel();
        employeePanel.setBackground(new Color(223, 239, 239));
        employeePanel.setBounds(0,0,200,700);
        addButtons(employeePanel);
    }

    private void addButtons(JPanel p) {
        button = new JButton();
        button.setBounds(30, 30, 150, 50);
        button.setText("Add Employee");
        button.addActionListener(this);
        p.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String employeeName = JOptionPane.showInputDialog("Enter the full name of the employee you wish to add:");
        }
    }

//    // MODIFIES: this
//    // EFFECTS: Asks user to enter employee name and creates Employee and puts into EmployeeRoster
//    private void addEmployee(String employeeName) {
//        Employee employee = new Employee(employeeName);
//        roster.addEmployee(employee);
//
//        System.out.println("Added " + employeeName + " to Employee Roster");
//
//        employeeMenu();
//    }
}
