package model;

import com.google.gson.Gson;

import dataStructures.Hash.Hash;

import exception.HashException;

import java.io.*;
import java.time.LocalDateTime;

public class Controller {

    private Hash<String, Passenger> passengers;

    private final int NUMBER_OF_SEATS = 48;

    public Controller() {
        readGson();
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

    public String passengerCheckIn() {
        LocalDateTime arrivalTime = LocalDateTime.now();
        return null; // To be completed
    }

}
