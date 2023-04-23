package model;

public class Passenger {

    private String name;
    private String lastName;
    private String identification;
    private PassengerClass passengerClass;
    private String seat;
    private int age, accumulatedMiles;
    private boolean specialAttention, checked;

    public Passenger(String name, String lastName, String identification, PassengerClass passengerClass, String seat,
            int age, int accumulatedMiles, boolean specialAttention, boolean checked) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.passengerClass = passengerClass;
        this.seat = seat;
        this.age = age;
        this.accumulatedMiles = accumulatedMiles;
        this.specialAttention = specialAttention;
        this.checked = checked;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFirstClass() {
        return passengerClass == PassengerClass.FIRST_CLASS;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nLast name: " + getLastName() + "\nId number: " + getIdentification()
                + "\nPassenger class: " + getPassengerClass() + "\nSeat: " + getSeat() + "\nAccumulated miles: "
                + getAccumulatedMiles() + "\nSpecial attention: " + isSpecialAttention() + "\n";
    }

}
