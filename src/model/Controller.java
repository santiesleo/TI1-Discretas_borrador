package model;

import com.google.gson.Gson;

import dataStructures.Hash.Hash;

import dataStructures.PriorityQueue.PriorityQueue;
import exception.HashException;

import java.io.*;
import java.time.Instant;

public class Controller {

	private Hash<String, Passenger> passengers;
	private final Hash<Character, Integer> seats;
	private final PriorityQueue<Integer, Passenger> entryOrder;
	private final PriorityQueue<Integer, Passenger> exitOrder;
	private final int startTime;

	private final int SEAT_PER_ROW = 6;

	public Controller() {
		Instant time = Instant.now();
		startTime = Math.toIntExact(time.getEpochSecond());
		readGson();
		entryOrder = new PriorityQueue<>();
		exitOrder = new PriorityQueue<>();
		seats = new Hash<>(SEAT_PER_ROW);
		fillHashSeats();
	}

	public void fillHashSeats() {
		seats.insert('A', 2);
		seats.insert('B', 4);
		seats.insert('C', 6);
		seats.insert('D', 5);
		seats.insert('E', 3);
		seats.insert('F', 1);
	}

	public void readGson() {
		Gson gson = new Gson();
		int NUMBER_OF_SEATS = 48;
		passengers = new Hash<>(NUMBER_OF_SEATS);
		File projectDir = new File(System.getProperty("user.dir"));
		File dataDirectory = new File(projectDir + "\\data");
		File passengerInformation = new File(dataDirectory + "\\data.json");

		if (!dataDirectory.exists()) {
			dataDirectory.mkdir();
		}
		try {
			if (!passengerInformation.exists()) {
				passengerInformation.createNewFile();
			}
			String json = new String(java.nio.file.Files.readAllBytes(passengerInformation.toPath()));
			Passenger[] passengersJson = gson.fromJson(json, Passenger[].class);
			for (Passenger passenger : passengersJson) {
				passengers.insert(passenger.getIdentification(), passenger);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (HashException he) {
			System.err.println(he.getMessage());
		}
	}

	public String searchPassenger(String identification) {
		Passenger passenger = passengers.search(identification);
		if (passenger == null) throw new RuntimeException("Passenger not found");
		return "Passenger information:\n" + passenger.toString();
	}

	public String passengerCheckIn(String identification) throws RuntimeException {
		Passenger passenger = passengers.search(identification);
		String msg;
		if (passenger == null) {
			throw new RuntimeException("Passenger not found");
		} else if (passenger.isChecked()) {
			throw new RuntimeException("The passenger had already been registered in the departure lounge");
		} else {
			Instant instant = Instant.now();
			int arrivalTime = Math.toIntExact(instant.getEpochSecond());
			int priority = calculatePriorityEntry(passenger, arrivalTime - startTime);
			passenger.setChecked(true);
			entryOrder.insert(priority, passenger);
			fillPQExit(passenger);
			msg = "Passenger checked in at boarding lounge";
		}
		return msg;
	}

	public int calculatePriorityEntry(Passenger passenger, int arrivalTime) {
		int priority = 0;
		int coefficient = 100000;
		String rowString = passenger.getSeat();
		int seat = Integer.parseInt(rowString.substring(1));
		priority += coefficient * seat;
		coefficient = 1;
		if (passenger.isFirstClass()) {
			if (passenger.isSpecialAttention()) {
				priority += coefficient * (passenger.getAge() >= 60 ? 5000 : 2500);
			} else if (passenger.getAge() >= 60) {
				priority += coefficient * 2500;
			}
			priority += coefficient * passenger.getAccumulatedMiles();
		}
		coefficient = 5;
		priority -= coefficient * arrivalTime;
		return priority;
	}

	public String showEntryOrder() {
		return getListsString(entryOrder);
	}

	public String showExitOrder() {
		return getListsString(exitOrder);
	}

	private String getListsString(PriorityQueue<Integer, Passenger> priorityQueue) {
		StringBuilder msg = new StringBuilder();
		PriorityQueue<Integer, Passenger> priorityQueueCopy = priorityQueue.clone();
		while (!priorityQueueCopy.isEmpty()) {
			Passenger passenger = priorityQueueCopy.extractMax();
			msg.append(String.format("%-10s %s\n",passenger.getSeat(), passenger.getName() + " " + passenger.getLastName()));
		}
		return msg.toString();
	}

	public int calculatePriorityExit(int row, int numSeat) {
		int TOTAL_ROWS = 8;
		return SEAT_PER_ROW * (TOTAL_ROWS - row) + numSeat;
	}

	public void fillPQExit(Passenger passenger) {
		String seat = passenger.getSeat();
		int numSeat = seats.search(seat.charAt(0));
		int row = seat.charAt(1);
		int priority = calculatePriorityExit(row, numSeat);
		exitOrder.insert(priority, passenger);
	}

}
