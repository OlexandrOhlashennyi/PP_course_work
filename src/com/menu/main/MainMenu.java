package com.menu.main;

import Product.TaxiPark;
import com.menu.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class MainMenu extends Application implements EventHandler<ActionEvent> {
    private LinkedHashMap<String, MenuCommand> menuItems;
    private LinkedHashMap<String, String> descr;
    private  Scanner scanner = new Scanner(System.in);
    private String s;

    @FXML
    Button show_all_btn;
    @FXML
    Button show_avail_btn;
    @FXML
    Button show_betw_btn;
    @FXML
    TextField lower_te;
    @FXML
    TextField upper_te;
    @FXML
    RadioButton speed_rbt;
    @FXML
    RadioButton price_rbt;

    private static TaxiPark TP;

    static {
        try {
            TP = new TaxiPark("TaxiPark1", "+380994130557", "taxipark1@taxi.com");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MainMenu() {
        s = TP.toString();
        menuItems = new LinkedHashMap<>();
        descr = new LinkedHashMap<>();
        menuItems.put(Add.NAME, new Add(TP));
        menuItems.put(Delete.NAME, new Delete(TP));
        menuItems.put(Order.NAME, new Order(TP));
        menuItems.put(Agr.NAME, new Agr(TP));
        menuItems.put(Show.NAME, new Show(TP));
        menuItems.put(Exit.NAME, new Exit(TP));
    }

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
        menuItems.put(Show.NAME, new Show(TP));
        descr.put(Show.NAME, Show.DESCR);
        menuItems.put(Exit.NAME, new Exit(TP));
        descr.put(Exit.NAME, Exit.DESCR);
    }

    public void execute() throws InterruptedException, IOException, SQLException {
        launch();
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

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Курсова робота Оглашенного Олександра КН-202");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @FXML
    public void handle(ActionEvent actionEvent) {

    }

    public void click(ActionEvent actionEvent) {
        if (actionEvent.getSource() == show_all_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                mc.execute(Arrays.asList("all"));
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == show_avail_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                mc.execute(Arrays.asList());
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == show_betw_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                double lower = Double.parseDouble(lower_te.getText());
                double upper = Double.parseDouble(upper_te.getText());
                if (speed_rbt.isSelected())
                    mc.execute(Arrays.asList("speed", "" + lower, "" + upper));
                else
                    mc.execute(Arrays.asList("price", "" + lower, "" + upper));
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
