package Blatt07;

import java.io.Serializable;


/**
 * Ueberklasse zu Mercedes und Porsche
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class Car implements Serializable {
    private static long serialVersionUID;
    protected String licensePlate;
    protected String productionDate;
    protected int numberPassengers;
    protected int numberWheels;
    protected int numberDoors;

    public Car(){

    }

    public Car(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors){
        this.licensePlate = licensePlate;
        this.productionDate = productionDate;
        this.numberPassengers = numberPassengers;
        this.numberWheels = numberWheels;
        this.numberDoors = numberDoors;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Car.serialVersionUID = serialVersionUID;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public void setNumberWheels(int numberWheels) {
        this.numberWheels = numberWheels;
    }

    public void setNumberDoors(int numberDoors) {
        this.numberDoors = numberDoors;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public int getNumberWheels() {
        return numberWheels;
    }

    public int getNumberDoors() {
        return numberDoors;
    }

    @Override
    public String toString(){
        return ("License plate: " + licensePlate +"\nProduction date: " + productionDate + "\nNumber of passengers: " + numberPassengers
                + "\nNumber of wheels: " + numberWheels + "\nNumber of doors: "+ numberDoors);
    }

}
