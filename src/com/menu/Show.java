package com.menu;

import Product.TaxiPark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Show implements MenuCommand{
    public final static String NAME = "show";
    public final static String DESCR = "by default this command will show available cars. For all cars:\n\t\tshow --all\n\tfor cars with speed more than 100 and less than 150:\n\t\tshow --speed --100 --150\n\tAs for speed, you can specify parameters like:\n\t\t--consumption --[min] --[max]\n\t\t--price --[min] --[max]";
    TaxiPark TP;

    public Show(TaxiPark TP)
    {
        this.TP = TP;
    }

    public ResultSet execute(List<String> pr) throws SQLException {
        if (pr.size() == 0)
        {
            return TP.printAvailableCars();
        }
        else if ("all".equals(pr.get(0)))
        {
            return TP.printAllCars();
        }
        else if ("speed".equals(pr.get(0)) && pr.size() == 3)
        {
            return TP.printCarsWithSpeed(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else if ("price".equals(pr.get(0)) && pr.size() == 3)
        {
            return TP.printCarsWithPrice(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else if ("consumption".equals(pr.get(0)) && pr.size() == 3)
        {
            return TP.printCarsWithConsum(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else
        {
            return TP.printAvailableCars();
        }
    }
}
