package com.menu;

import Product.TaxiPark;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Agr implements MenuCommand{
    public final static String NAME = "aggregate";
    public final static String DESCR = "for calculating aggregate functions, like:\n\t\taggregate --sum";
    TaxiPark TP;

    public Agr(TaxiPark TP)
    {
        this.TP = TP;
    }

    @Override
    public void execute(List<String> pr) throws InterruptedException {
        System.out.println("Total cost of TaxiPark: " + TP.sum() + "$");
    }
}
