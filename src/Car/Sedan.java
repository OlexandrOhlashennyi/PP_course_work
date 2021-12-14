package Car;

public class Sedan extends Car{
    public Sedan(String model, double price, double consumption, double max_velocity, int ID, boolean b) {
        super(model, price, consumption, max_velocity, ID, b);
        this.passengers = 4;
        this.driver_price = 5;
        this.fuel_price = 29.95;
    }

    @Override
    public double cost(double length, int mode) {
        return this.normalDrive(length);
    }

    @Override
    public String getType() {
        return "sedan";
    }

    @Override
    public String toString() {
        return "Sedan   \t--> " + super.toString();
    }
}
