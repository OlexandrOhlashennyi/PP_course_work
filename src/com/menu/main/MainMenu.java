package com.menu.main;

import Product.TaxiPark;
import com.menu.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private LinkedHashMap<String, MenuCommand> menuItems;
    private LinkedHashMap<String, String> descr;
    private  Scanner scanner = new Scanner(System.in);
    private String s;

    public MainMenu(TaxiPark TP)
    {
        s = TP.toString();
        menuItems = new LinkedHashMap<>();
        descr = new LinkedHashMap<>();
        menuItems.put(Add.NAME, new Add(TP));
        descr.put(Add.NAME, Add.DESCR);
        menuItems.put(Delete.NAME, new Delete(TP));
        descr.put(Delete.NAME, Delete.DESCR);
        menuItems.put(Order.NAME, new Order(TP));
        descr.put(Order.NAME, Order.DESCR);
        menuItems.put(Agr.NAME, new Agr(TP));
        descr.put(Agr.NAME, Agr.DESCR);
        menuItems.put(Sort.NAME, new Sort(TP));
        descr.put(Sort.NAME, Sort.DESCR);
        menuItems.put(Show.NAME, new Show(TP));
        descr.put(Show.NAME, Show.DESCR);
        menuItems.put(Load.NAME, new Load(TP));
        descr.put(Load.NAME, Load.DESCR);
        menuItems.put(Save.NAME, new Save(TP));
        descr.put(Save.NAME, Save.DESCR);
        menuItems.put(Exit.NAME, new Exit());
        descr.put(Exit.NAME, Exit.DESCR);
    }

    public void execute() throws InterruptedException, IOException {
        System.out.println("Welcome to TaxiPark organizer!\n" + s);
        while (true)
        {
            System.out.println("\nPlease enter command('help' to see command list'):");
            String ss = scanner.nextLine();
            List<String> pr = Arrays.asList(ss.split(" --"));
            MenuCommand mc = menuItems.get(pr.get(0));
            if (mc != null)
            {
                mc.execute(pr.subList(1,pr.size()));
            }
            else if ("help".equals(ss))
            {
                System.out.println("Here is the list of commands:");
                for (String name: descr.keySet()) {
                    System.out.println(name + "\t:- " + descr.get(name)+"\n");
                }
            }
            else
            {
                System.out.println("enter correct");
            }
        }
    }
}
