package Product;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;

import java.sql.*;

public class TaxiPark {
    private String name;
    private String phone;
    private String email;
    private int last_id = 100;

    private Connection con;

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

        last_id = c.getID();
    }

    public int getLast_id() {
        return last_id;
    }

    public void removeCar(int id) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from Cars where ID = " + id);
    }

    public void order(int i, boolean flag) throws SQLException {
        PreparedStatement stat = con.prepareStatement("update Cars set busy = ? " +
                " where ID = ?");
        stat.setBoolean(1, flag);
        stat.setInt(2, i);
        stat.executeUpdate();
    }

    public double sum() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select sum(price) as S from Cars");
        if (rs.next())
            return Double.parseDouble(rs.getString("S"));
        return 0;
    }

    public double cost(int i, double length, int mode) throws SQLException {
        PreparedStatement stat = con.prepareStatement("select * from Cars where ID = ?");
        stat.setInt(1, i);
        ResultSet rs = stat.executeQuery();
        Car c = null;
        if (rs.next())
        {
            if (rs.getString("type").contains("sport")) {
                c = new Sport(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
                return c.cost(length,mode);
            }
            else if (rs.getString("type").contains("minivan")) {
                c = new MiniVan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
                return c.cost(length,mode);
            }
            else if (rs.getString("type").contains("sedan")) {
                c = new Sedan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
                return c.cost(length,mode);
            }
        }
        return 0;
    }

    public void printAvailableCars() throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]\n" +
                "where busy = ?");
        stat.setBoolean(1, false);
        ResultSet sel = stat.executeQuery();

        printRS(sel);
    }

    public void printCarsWithSpeed(double min, double max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]\n" +
                "where max_velocity > ? and max_velocity < ?");
        stat.setFloat(1, (float) min);
        stat.setFloat(2, (float) max);
        ResultSet sel = stat.executeQuery();

        printRS(sel);
    }

    public void printCarsWithPrice(double min, double max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]\n" +
                "where price > ? and price < ?");
        stat.setFloat(1, (float) min);
        stat.setFloat(2, (float) max);
        ResultSet sel = stat.executeQuery();

        printRS(sel);
    }

    public void printCarsWithConsum(double min, double max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]\n" +
                "where consumption > ? and consumption < ?");
        stat.setFloat(1, (float) min);
        stat.setFloat(2, (float) max);
        ResultSet sel = stat.executeQuery();

        printRS(sel);
    }

    public void printAllCars() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet sel = stat.executeQuery("SELECT * \n" +
                "  FROM [taxipark].[dbo].[Cars]");

        printRS(sel);
    }

    private void printRS(ResultSet sel) throws SQLException {
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
