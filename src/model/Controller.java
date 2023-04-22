package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private ArrayList<Passenger> passengers;

    public Controller() {
        this.passengers = new ArrayList<>();
    }

    public void readGson() {
        Gson gson = new Gson();
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir + "/data");
        File result = new File(projectDir + "/data/data.json");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            String json = new String(java.nio.file.Files.readAllBytes(result.toPath()));
            Passenger[] countries = gson.fromJson(json, Passenger[].class);
            if (countries != null) {
                this.passengers.addAll(Arrays.asList(countries));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
