package com.menu;

import Product.TaxiPark;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Load implements MenuCommand{
    public final static String NAME = "load";
    public final static String DESCR = "for load config from file run this command with file name as parameter, like:\n\t\tload --C:/source.txt";
    TaxiPark TP;

    public Load(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException, FileNotFoundException {
        if (pr.size() != 1)
        {
            System.out.println("Fail..check your parameters");
            return;
        }
        int cnt = TP.loadConfig(pr.get(0));

        System.out.print("loading");
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        System.out.print("Done!   " + cnt + " cars was loaded");
    }
}
