package Blatt07;

/**
 * erweitert Car
 * Testklasse zur Demonstration von Serialisierung
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class Mercedes extends Car{
    private static long serialVersionUID;
    private String model;
    private int capacity;

    public Mercedes(){}
    public Mercedes(String licensePlate,String productionDate,int numberPassengers,int numberWheels,int numberDoors,String model,int capacity){
        super(licensePlate,productionDate,numberPassengers,numberWheels,numberDoors);
        this.model = model;
        this.capacity = capacity;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Mercedes.serialVersionUID = serialVersionUID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString(){
        return ("Modell: " + this.model + "\n" +"License plate: " + licensePlate +"\nProduction date: " + productionDate + "\nNumber of passengers: " + numberPassengers
                + "\nNumber of wheels: " + numberWheels + "\nNumber of doors: "+ numberDoors + "\nCapacity: " + this.capacity);
    }
}
