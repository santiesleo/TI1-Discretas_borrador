package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Controller;

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
        System.out.println(RED_BOLD + "*****************************************************************" + RESET);
        System.out.println(BOLD + "                   WELCOME TO BOARDING SYSTEM                    " + RESET);

        boolean stopFlag = false;

        while (!stopFlag) {
            System.out.println(RED_BOLD + "*****************************************************************" + RESET);
            System.out.println(BOLD + "MAIN MENU:" + RESET +
                    RED_BOLD + "\n[1]" + RESET + " Check in a passenger." +
                    RED_BOLD + "\n[2]" + RESET + " Show passenger entry order." +
                    RED_BOLD + "\n[3]" + RESET + " Display passenger exit order." +
                    RED_BOLD + "\n[4]" + RESET + " Exit."
            );
            try {
                System.out.print(GREEN_BOLD + "> " + RESET);
                int mainOption = Integer.parseInt(scanner.nextLine());
                System.out.println(RED_BOLD + "*****************************************************************" + RESET);

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
                        System.out.println(BOLD + "                       EXIT SUCCESSFULLY                        " + RESET);
                        System.out.println(RED_BOLD + "*****************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    default:
                        System.out.println(BOLD + "            INVALID INPUT: PLEASE ENTER A VALID VALUE            " + RESET);
                        break;
                }
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println(RED_BOLD + "*****************************************************************" + RESET);
                System.out.println(BOLD + "            INVALID INPUT: PLEASE ENTER A VALID VALUE            " + RESET);
            }
        }
    }

    public void passengerCheckIn() {
        System.out.print(BOLD + "Passenger ID number: " + RESET);
        String identification = scanner.nextLine();
        String passengerInformation = controller.searchPassenger(identification);
        System.out.println(passengerInformation);
        System.out.println(passengerInformation != null ? controller.passengerCheckIn(identification) : "");
    }

    public void showEntryOrder() {
        String msg = controller.showEntryOrder();
        if (msg.equals("")) {
            System.out.println(BOLD + "                   NO PASSENGER HAS CHECKED IN                   " + RESET);
        } else {
            System.out.print(BOLD + "ENTRY ORDER:" + RESET);
            System.out.println(msg);
        }
    }

    public void showExitOrder() {
        String msg = controller.showExitOrder();
        if (msg.equals("")) {
            System.out.println(BOLD + "              NO PASSENGER HAS ENTERED THE AIRCRAFT              " + RESET);
        } else {
            System.out.print(BOLD + "EXIT ORDER:" + RESET);
            System.out.println(msg);
        }
    }

}