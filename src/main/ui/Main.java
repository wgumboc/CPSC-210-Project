package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("couldn't load what you wanted");
        }
        new ManningScheduleAppGUI();

        // Prints log of events to console on close
        // taken from https://stackoverflow.com/questions/5824049/running-a-method-when-closing-the-program
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n\n");
                }
            }
        }, "Shutdown-thread"));
    }

}
