package com.company;

import Car.Car;
import Car.MiniVan;
import Car.Sport;
import Car.Sedan;
import Product.TaxiPark;
import com.menu.main.MainMenu;

import java.io.IOException;

public class Main {
    private static TaxiPark TP = new TaxiPark("TaxiPark1", "+380994130557", "taxipark1@taxi.com");

    public static void main(String[] args) throws InterruptedException, IOException {
        MainMenu mm = new MainMenu(TP);

        mm.execute();
    }
}
