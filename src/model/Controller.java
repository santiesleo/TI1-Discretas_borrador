package model;

import com.google.gson.Gson;

import dataStructures.Hash.Hash;

import dataStructures.PriorityQueue.PriorityQueue;
import exception.HashException;

import java.io.*;
import java.time.Instant;

public class Controller {

    private Hash<String, Passenger> passengers;
    private PriorityQueue<Integer, Passenger> entryOrder;
    private int startTime;

    private final int NUMBER_OF_SEATS = 48;

    public Controller() {
        Instant time = Instant.now(); // Calcula la hora de inicio del sistema
        startTime = Math.toIntExact(time.getEpochSecond()); // Convierte la hora del sistema a segundos / Se convierte a int porque getEpochSecond trabaja con long
        readGson();
        entryOrder = new PriorityQueue<>();
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
        return (passenger == null) ? "Error. Passenger not found." : passenger.toString();
    }


    public String passengerCheckIn(String identification) {
        Passenger passenger = passengers.search(identification);
        Instant instant = Instant.now();
        int arrivalTime = Math.toIntExact(instant.getEpochSecond());
        System.out.println(startTime - arrivalTime);
        int priority = calculatePriority(passenger, passenger.isFirstClass(), startTime - arrivalTime);
        System.out.println(priority);
        entryOrder.insert(priority, passenger);
        return null; // To be completed
    }

    public int calculatePriority(Passenger passenger, boolean isFirstClass, int arrivalTime) {
        int priority = 0;
        int coefficient = 1000; // Si quieren que organize desde la fila 8 hasta la fila 1. Cambienlo por 10000.
        // Cuando es 1000, le da más prioridad a la primera clase de la fila 3 a la 1. Y luego la clase economy de la fila 8 a la 4
        String rowString = passenger.getSeat();
        int seat = Integer.parseInt(rowString.substring(1));
        priority += coefficient * seat;
        System.out.println("Priority seat: " + priority);

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
        System.out.println("Priority date: " + coefficient * arrivalTime);
        priority += coefficient * arrivalTime;

        return priority;
    }

    public String showEntryOrder() {
        StringBuilder msg = new StringBuilder();
        while(!entryOrder.isEmpty()) {
            Passenger passenger = entryOrder.extractMax();
            msg.append(passenger.getName()).append(" ").append(passenger.getLastName()).append(" ").append(passenger.getSeat()).append("\n");
        }
        return msg.toString();
    }
}
