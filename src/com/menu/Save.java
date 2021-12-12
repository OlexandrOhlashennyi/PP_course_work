package com.menu;

import Product.TaxiPark;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Save implements MenuCommand{
    public final static String NAME = "save";
    public final static String DESCR = "for save config to file run this command with file name as parameter, like:\n\t\tload --C:/dest.txt";
    TaxiPark TP;

    public Save(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException, IOException {
        if (pr.size() != 1)
        {
            System.out.println("Fail..check your parameters");
            return;
        }
        TP.saveConfig(pr.get(0));

        System.out.print("saving");
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        System.out.print("Done! file name: " + pr.get(0));
    }
}
