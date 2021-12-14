package com.menu;

import Product.TaxiPark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exit implements MenuCommand {
    public final static String NAME = "exit";
    public final static String DESCR = "for exit the program -- run this command";
    TaxiPark TP;

    public Exit(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        TP.exit();

        System.exit(0);
        return null;
    }
}
