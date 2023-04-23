package ui;

import java.util.Scanner;

import model.Controller;

public class Main {
    private static Controller controller = new Controller();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        System.out.print(
                "1. Check in a passenger.\n2. Show passenger entry order.\n3. Display passenger departure order.\n0. Exit.\nOption: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        menu(option);
    }

    public static void menu(int option) {
        switch (option) {
            case 1:
                passengerCheckIn();
                break;
            case 2:
                showEntryOrder();
                break;
            case 3:
                break;
            case 0:
                System.out.println("Thanks for using the program!");
                break;
            default:
                System.out.println("Error. Type a valid option.");
                break;
        }
        if (option != 0) {
            menu();
        }
    }

    public static void passengerCheckIn() {
        System.out.print("Passenger id number: ");
        String identification = scanner.nextLine();
        String passengerInformation = controller.searchPassenger(identification);
        System.out.println(passengerInformation);
        System.out.println(passengerInformation != null ? controller.passengerCheckIn(identification) : "");
    }

    public static void showEntryOrder() {
        System.out.println(controller.showEntryOrder());
    }


}