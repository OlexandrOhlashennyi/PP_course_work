package Product;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TaxiPark {
    static FileWriter file_write;
    static File file_read;

    private String name;
    private String phone;
    private String email;
    private int last_id = 100;

    private Connection con;
    private ArrayList<Car> park = new ArrayList<Car>();

    public TaxiPark() throws SQLException {
         con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-VQVGTCO:1433;database=taxipark", "sa", "1234");
    }

    public TaxiPark(String name, String phone, String email) throws SQLException {
        this.name = name;
        this.phone = phone;
        this.email = email;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-VQVGTCO:1433;database=taxipark", "sa", "1234");
    }

    public void addCar(Car c) throws SQLException {
        String sql = "insert into Cars (type, name, price, consumption, max_velocity) " +
                "values(?, ?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, c.getType());
        stat.setString(2, c.getModel());
        stat.setFloat(3, (float) c.getPrice());
        stat.setFloat(4, (float) c.getConsumption());
        stat.setFloat(5, (float) c.getMax_velocity());

        stat.executeUpdate();

        park.add(c);
        last_id = c.getID();
    }

    public int getLast_id() {
        return last_id;
    }

    public void removeCar(int id) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from Cars where ID = " + id);
        /*for (int i = 0; i < park.size(); i++)
        {
            if (park.get(i).getID() == id)
            {
                park.remove(i);
                break;
            }
        }*/
    }

    public void order(int i, boolean flag)
    {
        for (Car c : park)
        {
            if  (c.getID() == i)
            {
                c.setBusy(flag);
                break;
            }
        }
    }

    public double sum()
    {
        double s = 0;
        for (Car c:park)
        {
            s += c.getPrice();
        }
        return s;
    }

    public double cost(int i, double length, int mode)
    {
        for (Car c : park)
        {
            if  (c.getID() == i)
            {
                return c.cost(length, mode);
            }
        }
        return 0;
    }
    
    public void reverse()
    {
        Collections.reverse(park);
    }
    
    public void sortByPrice()
    {
        park.sort(Comparator.comparing(o -> o.getPrice()));
    }

    public void sortByCons()
    {
        park.sort(Comparator.comparing(o -> o.getConsumption()));
    }

    public void sortByVelos()
    {
        park.sort(Comparator.comparing(o -> o.getMax_velocity()));
    }

    public void saveConfig(String file_name) throws IOException {
        File f = new File(file_name);
        f.createNewFile();
        file_write = new FileWriter(file_name);

        for (Car c : park)
        {
            String s = c.getType() + " --" + c.getID() + " --" + c.getModel() + " --" + c.getPrice() + " --" + c.getConsumption() + " --" + c.getMax_velocity() + "\n";
            file_write.write(s);
        }
        file_write.close();
    }

    public int loadConfig(String file_name) throws FileNotFoundException {
        file_read = new File(file_name);
        Scanner Input = new Scanner(file_read);

        park.clear();

        int cnt = 0;
        while (Input.hasNextLine()) {
            String ss = Input.nextLine();
            List<String> pr = Arrays.asList(ss.split(" --"));
            Car a = null;
            switch (pr.get(0))
            {
                case "sedan":
                    a = new Sedan(pr.get(2), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), Double.parseDouble(pr.get(5)), Integer.parseInt(pr.get(1)));
                    break;
                case "sport":
                    a = new Sport(pr.get(2), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), Double.parseDouble(pr.get(5)), Integer.parseInt(pr.get(1)));
                    break;
                case "minivan":
                    a = new MiniVan(pr.get(2), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), Double.parseDouble(pr.get(5)), Integer.parseInt(pr.get(1)));
                    break;
            }
            park.add(a);
            cnt++;
        }

        return cnt;
    }

    public void printAvailableCars()
    {
        System.out.println("\n\tAll available cars in TaxiPark:");
        int i = 0;
        for (Car c: park) {
            if (!c.isBusy())
                System.out.println(++i + ". " + c);
        }
        if (i == 0)
            System.out.println("empty");
    }

    public void printCarsWithSpeed(double min, double max)
    {
        System.out.println("\n\tAll cars in TaxiPark with speed between " + min + " and " + max + ":");
        int i = 0;
        for (Car c: park) {
            if (c.getMax_velocity() > min && c.getMax_velocity() < max)
                System.out.println(++i + ". " + c);
        }
        if (i == 0)
            System.out.println("empty");
    }

    public void printCarsWithPrice(double min, double max)
    {
        System.out.println("\n\tAll cars in TaxiPark with price between " + min + " and " + max + ":");
        int i = 0;
        for (Car c: park) {
            if (c.getPrice() > min && c.getPrice() < max)
                System.out.println(++i + ". " + c);
        }
        if (i == 0)
            System.out.println("empty");
    }

    public void printCarsWithConsum(double min, double max)
    {
        System.out.println("\n\tAll cars in TaxiPark with consumption between " + min + " and " + max + ":");
        int i = 0;
        for (Car c: park) {
            if (c.getConsumption() > min && c.getConsumption() < max)
                System.out.println(++i + ". " + c);
        }
        if (i == 0)
            System.out.println("empty");
    }

    public void printAllCars() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet sel = stat.executeQuery("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]");

        while (sel.next())
        {
            System.out.println(sel.getString("ID") +
                    " " + sel.getString("type") +
                    sel.getString("name") +
                    sel.getString("price") +
                    " " + sel.getString("busy"));
        }
    }

    public void exit() throws SQLException {
        con.close();
    }

    @Override
    public String toString() {
        return   name + "\n" +
                "\tphone='" + phone + "\n" +
                "\temail='" + email + "\n";
    }
}
