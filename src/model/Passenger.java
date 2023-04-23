package model;

public class Passenger {
    private String name;
    private String lastName;
    private String identification;
    private PassengerClass passengerClass;
    private String seat;
    private int accumulatedMiles;
    private boolean specialAttention;

    public Passenger(String name, String lastName, String identification, PassengerClass passengerClass, String seat,
            int accumulatedMiles, boolean specialAttention) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        // switch (passengerClass) {
        // case "ECONOMY":
        // this.passengerClass = PassengerClass.ECONOMY;
        // break;
        // case "FIRST_CLASS":
        // this.passengerClass = PassengerClass.FIRST_CLASS;
        // break;
        // }
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        // String passengerClass = (getPassengerClass() == PassengerClass.ECONOMY) ?
        // "Economy" : "First class";
        return "{" +
                " name='" + getName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", identification='" + getIdentification() + "'" +
                ", passengerClass='" + getPassengerClass() + "'" +
                ", seat='" + getSeat() + "'" +
                ", accumulatedMiles='" + getAccumulatedMiles() + "'" +
                ", specialAttention='" + isSpecialAttention() + "'" +
                "}";
    }

}
