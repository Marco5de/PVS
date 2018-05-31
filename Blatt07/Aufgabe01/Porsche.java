package Blatt07;

/**
 * erweitert Car
 * Testklasse zur Demonstration von Serialisierung
 *
 *  @author Benedikt Jutz
 *  @author Marco Deuscher
 */
public class Porsche extends Car{
    private  static long serialVersionUID;
    private String model;
    private int ps;

    public Porsche(){}
    public Porsche(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors, String model, int ps){
        super(licensePlate,productionDate,numberPassengers,numberWheels,numberDoors);
        this.model = model;
        this.ps = ps;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Porsche.serialVersionUID = serialVersionUID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public int getPs() {
        return ps;
    }

    @Override
    public String toString(){
        return ("Model: " + this.model +"\n"+ "License plate: " + licensePlate +"\nProduction date: " + productionDate + "\nNumber of passengers: " + numberPassengers
                + "\nNumber of wheels: " + numberWheels + "\nNumber of doors: "+ numberDoors + "\nPS: " + this.ps);
    }
}
