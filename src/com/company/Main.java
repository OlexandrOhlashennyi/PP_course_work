package com.company;

import Car.Car;
import Car.MiniVan;
import Car.Sport;
import Car.Sedan;
import Product.TaxiPark;
import com.menu.main.MainMenu;
import java.sql.*;

import java.io.IOException;

public class Main {
    private static TaxiPark TP = new TaxiPark("TaxiPark1", "+380994130557", "taxipark1@taxi.com");

    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-VQVGTCO:1433;database=taxipark", "sa", "1234");
        Statement stat = con.createStatement();
        stat.executeUpdate("insert into Cars (type, name, price, consumption, max_velocity) values('sedan', 'Model test', 10000, 4.5, 200)");

        ResultSet sel = stat.executeQuery("SELECT TOP (1000) [ID]\n" +
                "      ,[type]\n" +
                "      ,[name]\n" +
                "      ,[price]\n" +
                "      ,[consumption]\n" +
                "      ,[max_velocity]\n" +
                "  FROM [taxipark].[dbo].[Cars]");

        while (sel.next())
        {
            System.out.println(sel.getString("ID") + sel.getString("type") + sel.getString("name") + sel.getString("price"));
        }

        stat.executeUpdate("delete from Cars where name like '%test%'");


        ResultSet sel2 = stat.executeQuery("SELECT TOP (1000) [ID]\n" +
                "      ,[type]\n" +
                "      ,[name]\n" +
                "      ,[price]\n" +
                "      ,[consumption]\n" +
                "      ,[max_velocity]\n" +
                "  FROM [taxipark].[dbo].[Cars]");

        while (sel2.next())
        {
            System.out.println(sel2.getString("ID") + sel2.getString("type") + sel2.getString("name") + sel2.getString("price"));
        }

        MainMenu mm = new MainMenu(TP);

        con.close();
        //mm.execute();
    }
}
