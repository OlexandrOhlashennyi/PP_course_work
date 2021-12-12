package com.company;

import Car.Car;
import Car.MiniVan;
import Car.Sport;
import Car.Sedan;
import Product.TaxiPark;
import com.menu.main.MainMenu;
import javafx.application.Application;

import java.sql.*;

import java.io.IOException;

public class Main {
    /*private static TaxiPark TP;

    static {
        try {
            TP = new TaxiPark("TaxiPark1", "+380994130557", "taxipark1@taxi.com");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        MainMenu mm = new MainMenu();
        mm.execute();
    }
}
