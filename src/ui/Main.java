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
		JOptionPane.showMessageDialog(null, "*****************************************************************\n                    WELCOME TO BOARDING SYSTEM                    \n*****************************************************************", "Passenger management system", JOptionPane.PLAIN_MESSAGE);
		int input;
		do {
			String[] menu = {"Check in a passenger", "Show entry order", "Show exit order", "Exit"};

			input = 1 + JOptionPane.showOptionDialog(null, new JLabel("Main menu. Select an option", JLabel.CENTER), "Choose an option :)", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);

			try {
				switch (input) {
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
//						String exit = "";
//						exit += ("*****************************************************************\n");
//						exit += ("                            EXIT SUCCESSFULLY                    \n");
//						exit += ("*****************************************************************");
//						JOptionPane.showMessageDialog(null, exit);
						break;
					default:
						throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				String invalidInput = "";
				invalidInput += ("*****************************************************************\n");
				invalidInput += ("            INVALID INPUT: PLEASE ENTER A VALID VALUE            \n");
				invalidInput += ("*****************************************************************");
				JOptionPane.showMessageDialog(null, invalidInput, "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (input != 4);
	}

	public void passengerCheckIn() {
		String identification = JOptionPane.showInputDialog("Passenger ID number: ");
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
		String entryOrder = ("*****************************************************************\n");
		if (msg.isEmpty()) {
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
		if (msg.isEmpty()) {
			exitOrder += ("              NO PASSENGER HAS ENTERED THE AIRPLANE              \n");
		} else {
			exitOrder += ("EXIT ORDER:\n" + msg + "\n");
		}
		exitOrder += ("*****************************************************************");
		JOptionPane.showMessageDialog(null, exitOrder);
	}

}