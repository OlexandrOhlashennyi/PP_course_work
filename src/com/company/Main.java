package com.company;

import com.menu.main.MainMenu;

import java.sql.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        MainMenu mm = new MainMenu();
        mm.execute();
    }
}
