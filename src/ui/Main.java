package ui;

import model.Controller;

public class Main {
    private static Controller controller = new Controller();

    public static void main(String[] args) {
        System.out.println(controller.searchPassenger("12345678"));
        System.out.println(controller.searchPassenger("89012345"));
        System.out.println(controller.searchPassenger("56473829"));
        System.out.println(controller.searchPassenger("98273465"));
        System.out.println(controller.searchPassenger("75849361"));
        System.out.println(controller.searchPassenger("78654329"));

    }

}