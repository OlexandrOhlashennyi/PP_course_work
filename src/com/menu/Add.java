package com.menu;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;
import Product.TaxiPark;

import java.sql.ResultSet;
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
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        if (pr.size() != 5)
        {
            return null;
        }
        Car a = null;
        switch (pr.get(0).toLowerCase(Locale.ROOT)) {
            case "sport":
                a = new Sport(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1, false);
                break;
            case "sedan":
                a = new Sedan(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1, false);
                break;
            case "minivan":
                a = new MiniVan(pr.get(1), Double.parseDouble(pr.get(2)), Double.parseDouble(pr.get(3)), Double.parseDouble(pr.get(4)), TP.getLast_id() + 1, false);
                break;
            default:
                return null;
        }
        TP.addCar(a);

        return null;
    }
}
