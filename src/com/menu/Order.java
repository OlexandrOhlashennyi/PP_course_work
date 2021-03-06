package com.menu;

import Product.TaxiPark;

import java.sql.ResultSet;
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
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
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
        } else
        {
            if (pr.size() > 1 && "order".equals(pr.get(0))) {
                TP.order(Integer.parseInt(pr.get(1)), true);
            } else if (pr.size() > 1 && "unorder".equals(pr.get(0))) {
                TP.order(Integer.parseInt(pr.get(1)), false);
            }
        }
        return null;
    }
}
