package com.menu;

import Product.TaxiPark;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Order implements MenuCommand {
    public final static String NAME = "order";
    public final static String DESCR = "for request a car or see drive price run this command with car ID and another proper parameter, like:\n\tFor check price:\n\t\torder --price --[ID] --[drive length in  km] --mode [can be 'normal', 'cargo' or 'extreme']\n\tFor order car:\n\t\torder --order --[ID]\n\tFor free car:\n\t\torder --unorder --[ID]";
    TaxiPark TP;

    public Order(TaxiPark TP) {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException, SQLException {
        if (pr.size() == 4 && "price".equals(pr.get(0))) {
            double length = Double.parseDouble(pr.get(2));
            int m;
            String mode = pr.get(3);
            switch(mode) {
                case "normal":
                    m = 1;
                    break;
                case "cargo":
                    m = 2;
                    break;
                case "extreme":
                    m = 3;
                    break;
                default:
                    m = 1;
                    break;
            }
            System.out.println("Order this car for drive " + length + "km in " + mode + " mode, will cost: " + TP.cost(Integer.parseInt(pr.get(1)), length, m) + "UAH");
        } else
        {
            System.out.print("processing");
            for (int i = 0; i < 4; i++)
            {
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.print(".");
            }
            if (pr.size() > 1 && "order".equals(pr.get(0))) {
                TP.order(Integer.parseInt(pr.get(1)), true);
                System.out.print("Done");
            } else if (pr.size() > 1 && "unorder".equals(pr.get(0))) {
                TP.order(Integer.parseInt(pr.get(1)), false);
                System.out.print("Done");
            } else {
                System.out.print("Fail...check your command");
            }
        }
    }
}
