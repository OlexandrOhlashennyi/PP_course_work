package com.menu;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exit implements MenuCommand {
    public final static String NAME = "exit";
    public final static String DESCR = "for exit the program -- run this command";
    @Override
    public void execute(List<String> pr) throws InterruptedException {
        System.out.print("exiting");
        for (int i = 0; i < 4; i++)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(".");
        }
        System.out.println("Have a nice day! Bye!");
        System.exit(0);
    }
}
