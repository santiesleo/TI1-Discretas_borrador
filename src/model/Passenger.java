package model;

public class Passenger {
    private String name;
    private String lastname;
    private String identification;
    private PassengerClass passengerClass;
    private String seat;
    private int accumulatedMiles;
    private boolean specialAttention;

    public Passenger(String name, String lastname, String identification, PassengerClass passengerClass, String seat, int accumulatedMiles, boolean specialAttention) {
        this.name = name;
        this.lastname = lastname;
        this.identification = identification;
        this.passengerClass = passengerClass;
        this.seat = seat;
        this.accumulatedMiles = accumulatedMiles;
        this.specialAttention = specialAttention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public PassengerClass getPassengerClass() {
        return passengerClass;
    }

    public void setPassengerClass(PassengerClass passengerClass) {
        this.passengerClass = passengerClass;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getAccumulatedMiles() {
        return accumulatedMiles;
    }

    public void setAccumulatedMiles(int accumulatedMiles) {
        this.accumulatedMiles = accumulatedMiles;
    }

    public boolean isSpecialAttention() {
        return specialAttention;
    }

    public void setSpecialAttention(boolean specialAttention) {
        this.specialAttention = specialAttention;
    }
}
