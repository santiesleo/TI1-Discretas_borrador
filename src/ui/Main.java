package ui;

import model.Controller;

import javax.swing.*;

public class Main {

    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        Main manager = new Main();
        manager.menu();
    }

    public void menu() {
        JOptionPane.showMessageDialog(null, "*****************************************************************\n                   WELCOME TO BOARDING SYSTEM                    \n*****************************************************************");

        boolean stopFlag = false;
        boolean menuFlag = false;
        while (!stopFlag) {
            String menu = "";
            menu += ("MAIN MENU:");
            menu += ("\n[1] Check in a passenger.");
            menu += ("\n[2] Show passenger entry order.");
            menu += ("\n[3] Exit.");

            String input = JOptionPane.showInputDialog(menu);

            if (input == null) {
                stopFlag = true;
                continue;
            }

            try {
                int mainOption = Integer.parseInt(input);
                switch (mainOption) {
                    case 1:
                        passengerCheckIn();
                        break;
                    case 2:
                        String alert = "";
                        alert += ("Once the entry list is displayed, no more passengers can be entered.");
                        JOptionPane.showMessageDialog(null, alert);
                        showEntryOrder();
                        stopFlag = subMenu();
                        break;
                    case 3:
                        String exit = "";
                        exit += ("*****************************************************************\n");
                        exit += ("                              EXIT SUCCESSFULLY                  \n");
                        exit += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, exit);
                        stopFlag = true;
                        break;
                    default:
                        String invalidInput = "";
                        invalidInput += ("*****************************************************************\n");
                        invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
                        invalidInput += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, invalidInput, "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException ex) {
                String invalidInput = "";
                invalidInput += ("*****************************************************************\n");
                invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
                invalidInput += ("*****************************************************************");
                JOptionPane.showMessageDialog(null, invalidInput, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean subMenu() {

        boolean stopFlag = false;
        while (!stopFlag) {
            String menu = "";
            menu += ("MAIN MENU:");
            menu += ("\n[1] Display passenger exit order.");
            menu += ("\n[2] Exit.");

            String input = JOptionPane.showInputDialog(menu);

            if (input == null) {
                stopFlag = true;
                continue;
            }

            try {
                int mainOption = Integer.parseInt(input);
                switch (mainOption) {
                    case 1:
                        showExitOrder();
                        break;
                    case 2:
                        String exit = "";
                        exit += ("*****************************************************************\n");
                        exit += ("                              EXIT SUCCESSFULLY                  \n");
                        exit += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, exit);
                        stopFlag = true;
                        break;
                    default:
                        String invalidInput = "";
                        invalidInput += ("*****************************************************************\n");
                        invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
                        invalidInput += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, invalidInput, "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException ex) {
                String invalidInput = "";
                invalidInput += ("*****************************************************************\n");
                invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
                invalidInput += ("*****************************************************************");
                JOptionPane.showMessageDialog(null, invalidInput, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return stopFlag;
    }

    public void passengerCheckIn() {
        String identification = JOptionPane.showInputDialog("Passenger ID number: ");
        try {
            String passengerInformation = controller.searchPassenger(identification);
            if (!passengerInformation.equals("Error: Passenger not found.")) {
                JOptionPane.showMessageDialog(null, passengerInformation);
                String checkInResult = controller.passengerCheckIn(identification);
                if (!checkInResult.equals("The passenger had already been registered in the departure lounge.")) {
                    JOptionPane.showMessageDialog(null, checkInResult);
                } else {
                    JOptionPane.showMessageDialog(null, checkInResult, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, passengerInformation, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ignored) {}
    }

    public void showEntryOrder() {
        String msg = controller.showEntryOrder();
        String entryOrder = ("*****************************************************************\n");
        if (msg.equals("")) {
            entryOrder += ("                   NO PASSENGER HAS CHECKED IN                   \n");
        } else {
            entryOrder += ("ENTRY ORDER:\n" + msg + "\n");
        }
        entryOrder += ("*****************************************************************");
        JOptionPane.showMessageDialog(null, entryOrder);
    }

    public void showExitOrder() {
        String msg = controller.showExitOrder();
        String exitOrder = ("*****************************************************************\n");
        if (msg.equals("")) {
            exitOrder += ("              NO PASSENGER HAS ENTERED THE AIRPLANE              \n");
        } else {
            exitOrder += ("EXIT ORDER:\n" + msg + "\n");
        }
        exitOrder += ("*****************************************************************");
        JOptionPane.showMessageDialog(null, exitOrder);
    }

}