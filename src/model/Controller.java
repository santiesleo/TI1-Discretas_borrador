package model;

import com.google.gson.Gson;

import dataStructures.Hash.Hash;

import dataStructures.PriorityQueue.PriorityQueue;
import exception.HashException;

import java.io.*;
import java.time.Instant;

public class Controller {

    private Hash<String, Passenger> passengers;
    Hash<Character, Integer> seats;
    private PriorityQueue<Integer, Passenger> entryOrder;
    private PriorityQueue<Integer, Passenger> exitOrder;
    private int startTime;

    private final int NUMBER_OF_SEATS = 48;
    private final int SEAT_PER_ROW = 6;
    private final int TOTAL_ROWS = 48;

    public Controller() {
        Instant time = Instant.now(); // Calcula la hora de inicio del sistema
        startTime = Math.toIntExact(time.getEpochSecond()); // Convierte la hora del sistema a segundos / Se convierte a
                                                            // int porque getEpochSecond trabaja con long
        readGson();
        entryOrder = new PriorityQueue<>();
        exitOrder = new PriorityQueue<>();
        seats = new Hash<>(SEAT_PER_ROW);
    }

    public void readGson() {
        Gson gson = new Gson();
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
        return (passenger == null) ? "Error. Passenger not found."
                : "Passenger information:\n" + passenger.toString();
    }

    public String passengerCheckIn(String identification) {
        String msg = "";
        Passenger passenger = passengers.search(identification);
        if (passenger.isChecked()) {
            msg = "The passenger has already checked in at the boarding lounge.";
        } else {
            Instant instant = Instant.now();
            int arrivalTime = Math.toIntExact(instant.getEpochSecond());
            int priority = calculatePriorityEntry(passenger, passenger.isFirstClass(), startTime - arrivalTime);
            passenger.setChecked(true);
            entryOrder.insert(priority, passenger);
            msg = "Passenger checked in at boarding lounge.";
        }
        return msg;
    }

    public int calculatePriorityEntry(Passenger passenger, boolean isFirstClass, int arrivalTime) {
        int priority = 0;
        int coefficient = 1000; // Si quieren que organize desde la fila 8 hasta la fila 1. Cambienlo por 10000.
        // Cuando es 1000, le da más prioridad a la primera clase de la fila 3 a la 1. Y
        // luego la clase economy de la fila 8 a la 4
        String rowString = passenger.getSeat();
        int seat = Integer.parseInt(rowString.substring(1));
        priority += coefficient * seat;
        coefficient = 1;
        if (isFirstClass) {
            if (passenger.isSpecialAttention()) {
                priority += coefficient * (passenger.getAge() >= 60 ? 5000 : 2500);
            } else if (passenger.getAge() >= 60) {
                priority += coefficient * 2500;
            }
            priority += coefficient * passenger.getAccumulatedMiles();
        }
        coefficient = 5;
        priority += coefficient * arrivalTime;
        return priority;
    }

    public String showEntryOrder() {
        StringBuilder msg = new StringBuilder();
        fillHash_seats();
        while (!entryOrder.isEmpty()) {
            Passenger passenger = entryOrder.extractMax();
            fillPQ_exit(passenger);
            msg.append("\n").append(passenger.getName()).append(" ").append(passenger.getLastName()).append(" ")
                    .append(passenger.getSeat());
        }
        return msg.toString();
    }

    public int calculatePriorityExit(int row, int numSeat) {
        return SEAT_PER_ROW * (TOTAL_ROWS - row) + numSeat;
    }

    public void fillHash_seats() {
        seats.insert('A', 4);
        seats.insert('B', 5);
        seats.insert('C', 6);
        seats.insert('D', 3);
        seats.insert('E', 2);
        seats.insert('F', 1);
    }

    public void fillPQ_exit(Passenger passenger) {
        String seat = passenger.getSeat();
        int numSeat = seats.search(seat.charAt(0));
        int row = seat.charAt(1);
        int priority = calculatePriorityExit(row, numSeat);
        exitOrder.insert(priority, passenger);
    }

    public String showExitOrder() {
        StringBuilder msg = new StringBuilder();
        while (!exitOrder.isEmpty()) {
            Passenger passenger = exitOrder.extractMax();
            msg.append("\n").append(passenger.getName()).append(" ").append(passenger.getLastName()).append(" ")
                    .append(passenger.getSeat());
        }
        return msg.toString();
    }

}
