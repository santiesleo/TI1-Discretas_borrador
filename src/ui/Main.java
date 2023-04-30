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
		JOptionPane.showMessageDialog(null, new JLabel("WELCOME TO BOARDING SYSTEM", JLabel.CENTER), "Passenger management system", JOptionPane.PLAIN_MESSAGE);
		int option;
		do {
			String[] menu = {"Check in a passenger", "Show entry order", "Show exit order", "Exit"};
			option = JOptionPane.showOptionDialog(null, new JLabel("Select an option", JLabel.CENTER), "Main menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
			switch (option) {
				case 0 -> passengerCheckIn();
				case 1 -> showEntryOrder();
				case 2 -> showExitOrder();
				case JOptionPane.CLOSED_OPTION -> option = 3;
			}
		} while (option != 3);
	}

	public void passengerCheckIn() {
		String identification = JOptionPane.showInputDialog("Passenger ID number: ");
		if (identification == null) {
			return;
		}
		try {
			String passengerInformation = controller.searchPassenger(identification);
			JOptionPane.showMessageDialog(null, passengerInformation);
			String checkInResult = controller.passengerCheckIn(identification);
			JOptionPane.showMessageDialog(null, checkInResult);
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void showEntryOrder() {
		String msg = controller.showEntryOrder();
		if (msg.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No passenger has checked in", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String entryOrder = ("ENTRY ORDER:\n" + msg + "\n");
			JOptionPane.showMessageDialog(null, entryOrder);
		}
	}

	public void showExitOrder() {
		String msg = controller.showExitOrder();
		if (msg.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No passenger has entered the airplane", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String exitOrder = ("EXIT ORDER:\n" + msg + "\n");
			JOptionPane.showMessageDialog(null, exitOrder);
		}
	}

}