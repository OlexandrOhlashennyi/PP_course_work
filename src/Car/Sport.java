package Car;

public class Sport extends Car{
    public Sport(String model, double price, double consumption, double max_velocity, int ID, boolean b) {
        super(model, price, consumption, max_velocity, ID, b);
        this.passengers = 2;
        this.driver_price = 20;
        this.fuel_price = 35;
    }

    @Override
    public double cost(double length, int mode) {
        switch(mode)
        {
            case 1:
                return this.normalDrive(length);
            case 3:
                return this.extremeDrive(length);
        }
        return 0;
    }

    @Override
    public String getType() {
        return "sport";
    }

    public double extremeDrive(double length)
    {
        return normalDrive(length) * 2;
    }

    @Override
    public String toString() {
        return "SportCar\t--> " + super.toString();
    }
}
