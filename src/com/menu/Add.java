package com.menu;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;
import Product.TaxiPark;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Add implements MenuCommand{
    public final static String NAME = "add";
    public final static String DESCR = "for adding new car to the park, type this command with proper parameters in order: model, price, consumption, max velocity,"
        + "like:\n\t\t add --type ['sport', 'sedan' or 'minivan'] --'model' --[price] --[consumption] --[max_velocity]";
    TaxiPark TP;

    public Add(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException, SQLException {
        if (pr.size() != 5)
        {
            System.out.println("Fail...check your command and try again");
            return;
        }

        System.out.print("ading");
        for (int i = 0; i < 4; i++) {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        Car a = null;
        switch (pr.get(0).toLowerCase(Locale.ROOT)) {
            case "sport":
                a = new Sport(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1);
                break;
            case "sedan":
                a = new Sedan(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1);
                break;
            case "minivan":
                a = new MiniVan(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1);
                break;
            default:
                System.out.println("Fail...check your command and try again");
                return;
        }
        TP.addCar(a);
        System.out.println("Done");
    }
}
