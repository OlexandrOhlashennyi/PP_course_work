package Car;

public class MiniVan extends Car{
    public MiniVan(String model, double price, double consumption, double max_velocity, int ID) {
        super(model, price, consumption, max_velocity, ID);
        this.passengers = 8;
        this.driver_price = 10;
        this.fuel_price = 31.45;
    }

    @Override
    public double cost(double length, int mode) {
        switch(mode)
        {
            case 1:
                return this.normalDrive(length);
            case 2:
                return this.cargoDrive(length);
        }
        return 0;
    }

    @Override
    public String getType() {
        return "minivan";
    }

    public double cargoDrive(double length)
    {
        return normalDrive(length) * 1.5;
    }

    @Override
    public String toString() {
        return "MiniVan \t--> " + super.toString();
    }
}
