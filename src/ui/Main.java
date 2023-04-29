package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Controller;

import javax.swing.*;

public class Main {

    private static final Controller controller = new Controller();
    private static final Scanner scanner = new Scanner(System.in);

    // Colors for console output
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";

    public static void main(String[] args) {
        Main manager = new Main();
        manager.menu();
    }

    public void menu() {
        JOptionPane.showMessageDialog(null, "*****************************************************************\n                   WELCOME TO BOARDING SYSTEM                    \n*****************************************************************");
        System.out.println(RED_BOLD + "*****************************************************************" + RESET);
        System.out.println(BOLD + "                   WELCOME TO BOARDING SYSTEM                    " + RESET);

        boolean stopFlag = false;

        while (!stopFlag) {
            String menu = "";
            menu += ("MAIN MENU:");
            menu += ("\n[1] Check in a passenger.");
            menu += ("\n[2] Show passenger entry order.");
            menu += ("\n[3] Display passenger exit order.");
            menu += ("\n[4] Exit.");
            System.out.println(RED_BOLD + "*****************************************************************" + RESET);
            System.out.println(BOLD + "MAIN MENU:" + RESET +
                    RED_BOLD + "\n[1]" + RESET + " Check in a passenger." +
                    RED_BOLD + "\n[2]" + RESET + " Show passenger entry order." +
                    RED_BOLD + "\n[3]" + RESET + " Display passenger exit order." +
                    RED_BOLD + "\n[4]" + RESET + " Exit.");
            try {
                System.out.print(GREEN_BOLD + "> " + RESET);
                int mainOption = Integer.parseInt(JOptionPane.showInputDialog(menu));
                System.out.println(
                        RED_BOLD + "*****************************************************************" + RESET);
                switch (mainOption) {
                    case 1:
                        passengerCheckIn();
                        break;
                    case 2:
                        showEntryOrder();
                        break;
                    case 3:
                        showExitOrder();
                        break;
                    case 4:
                        String exit = "";
                        exit += ("*****************************************************************\n");
                        exit += ("                       EXIT SUCCESSFULLY                         \n");
                        exit += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, exit);
                        System.out.println(
                                BOLD + "                       EXIT SUCCESSFULLY                        " + RESET);
                        System.out.println(
                                RED_BOLD + "*****************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    default:
                        String invalidInput = "";
                        invalidInput += ("*****************************************************************\n");
                        invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
                        invalidInput += ("*****************************************************************");
                        JOptionPane.showMessageDialog(null, invalidInput);
                        System.out.println(
                                BOLD + "            INVALID INPUT: PLEASE ENTER A VALID VALUE            " + RESET);
                        break;
                }
            } catch (InputMismatchException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "*****************************************************************\n" + "            INVALID INPUT: PLEASE ENTER A VALID VALUE            ");
                System.out.println(
                        RED_BOLD + "*****************************************************************" + RESET);
                System.out.println(BOLD + "            INVALID INPUT: PLEASE ENTER A VALID VALUE            " + RESET);
            }
        }
    }

    public void passengerCheckIn() {
        System.out.print(BOLD + "Passenger ID number: " + RESET);
        String identification = JOptionPane.showInputDialog("Passenger ID number: ");
        String passengerInformation = controller.searchPassenger(identification);
        JOptionPane.showMessageDialog(null, passengerInformation);
        JOptionPane.showMessageDialog(null, passengerInformation != null ? controller.passengerCheckIn(identification) : "");
        System.out.println(passengerInformation);
        System.out.println(passengerInformation != null ? controller.passengerCheckIn(identification) : "");
    }

    public void showEntryOrder() {
        String msg = controller.showEntryOrder();
        String entryOrder = ("*****************************************************************\n");
        if (msg.equals("")) {
            entryOrder += ("                   NO PASSENGER HAS CHECKED IN                   \n");
            System.out.println(BOLD + "                   NO PASSENGER HAS CHECKED IN                   " + RESET);
        } else {
            entryOrder += ("ENTRY ORDER:\n" + msg + "\n");
            System.out.print(BOLD + "ENTRY ORDER:" + RESET);
            System.out.println(msg);
        }
        entryOrder += ("*****************************************************************");
        JOptionPane.showMessageDialog(null, entryOrder);
    }

    public void showExitOrder() {
        String msg = controller.showExitOrder();
        String exitOrder = ("*****************************************************************\n");
        if (msg.equals("")) {
            exitOrder += ("              NO PASSENGER HAS ENTERED THE AIRCRAFT              ");
            System.out.println(BOLD + "              NO PASSENGER HAS ENTERED THE AIRCRAFT              " + RESET);
        } else {
            exitOrder += ("EXIT ORDER:\n" + msg + "\n");
            System.out.print(BOLD + "EXIT ORDER:" + RESET);
            System.out.println(msg);
        }
        exitOrder += ("*****************************************************************");
        JOptionPane.showMessageDialog(null, exitOrder);
    }

}