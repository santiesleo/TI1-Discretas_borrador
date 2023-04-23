package model;

import com.google.gson.Gson;

import dataStructures.Hash.Hash;
import exception.HashException;

import java.io.*;

public class Controller {

    private Hash<String, Passenger> passengers;

    public Controller() {
        readGson();
    }

    public void readGson() {
        Gson gson = new Gson();
        passengers = new Hash<>(48);
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

    public Passenger searchPassenger(String identification) {
        return passengers.search(identification);
    }

}
