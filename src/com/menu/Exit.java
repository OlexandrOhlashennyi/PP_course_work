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
        System.out.print("exiting");
        TP.exit();
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        System.out.println("Have a nice day! Bye!");
        System.exit(0);
        return null;
    }
}
