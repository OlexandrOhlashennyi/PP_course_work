package Car;

public abstract class Car {
    protected int ID = 0;
    protected double fuel_price = 29.95;
    protected double driver_price = 0;
    protected String model;
    protected Integer passengers;
    protected double price;
    protected double consumption;
    protected double max_velocity;
    protected boolean busy;

    public Car(String model, double price, double consumption, double max_velocity, int ID) {
        this.model = model;
        this.price = price;
        this.consumption = consumption;
        this.max_velocity = max_velocity;
        this.ID = ID;
        this.busy = false;
    }

    protected Car() {
    }

    public abstract double cost(double length, int mode);

    public abstract String getType();

    public double normalDrive(double length)
    {
        return (length * ((consumption / 100.0) * fuel_price + driver_price));
    }

    public int getID()
    {
        return ID;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public void setBusy(boolean f)
    {
        busy = f;
    }

    public String getModel() {
        return model;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public double getPrice() {
        return price;
    }

    public double getConsumption() {
        return consumption;
    }

    public double getMax_velocity() {
        return max_velocity;
    }

    @Override
    public String toString() {
        return "ID - " + ID + ": {  " +
                "model = '" + model + '\'' +
                "; passengers = " + passengers +
                " p.; price = " + price +
                " $; consumption = " + consumption +
                " l/100km; max_velocity = " + max_velocity +
                " km/h;  " + ((busy) ? "Not available" : "Available") + " }";
    }
}
