package com.menu;

import Product.TaxiPark;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Delete implements MenuCommand{
    public final static String NAME = "delete";
    public final static String DESCR = "for removing car from Park, type this command with index of car as parameter, like:\n\t\tdelete --[ID]";
    TaxiPark TP;

    public Delete(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException {
        TP.removeCar(Integer.parseInt(pr.get(0)));

        System.out.print("deleting");
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        System.out.println("Done");
    }
}