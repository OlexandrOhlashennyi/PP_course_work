package com.menu;

import Product.TaxiPark;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sort implements MenuCommand {
    public final static String NAME = "sort";
    public final static String DESCR = "for sorting cars, avalible parameters: max speed; price, consump, like:\n\t\tsort --price\n\tby default it sorted in ascending order, to sort  in descending - apply '--desc' in the end";
    TaxiPark TP;

    public Sort(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException {
        System.out.print("sorting");
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        switch (pr.get(0)) {
            case "price":
                TP.sortByPrice();
                break;
            case"consump":
                TP.sortByCons();
                break;
            case "max speed":
                TP.sortByVelos();
                break;
            default:
                System.out.println("Fail...check your command and try again");
                return;
        }
        if (pr.size() > 1 && "desc".equals(pr.get(1)))
        {
            TP.reverse();
        }

        System.out.println("Done");
    }
}
