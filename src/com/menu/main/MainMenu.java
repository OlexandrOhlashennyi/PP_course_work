package com.menu.main;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;
import Product.TaxiPark;
import com.menu.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class MainMenu extends Application implements EventHandler<ActionEvent> {
    private LinkedHashMap<String, MenuCommand> menuItems;
    private LinkedHashMap<String, String> descr;
    private  Scanner scanner = new Scanner(System.in);
    private String s;

    TableColumn id = new TableColumn("ID");
    TableColumn type = new TableColumn("type");
    TableColumn name = new TableColumn("name");
    TableColumn price = new TableColumn("price");
    TableColumn consumption = new TableColumn("consumption");
    TableColumn max_velocity = new TableColumn("max_velocity");

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
    @FXML
    TableView cars_table;

    private ObservableList<Car> cardata = FXCollections.observableArrayList();

    public ObservableList<Car> getCardata() {
        return cardata;
    }

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
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Курсова робота Оглашенного Олександра КН-202");
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();
    }

    @FXML
    public void handle(ActionEvent actionEvent) {

    }

    @FXML
    public void initialize()
    {
        cars_table.getColumns().addAll(id,type,name,price,consumption,max_velocity);
    }

    public void click(ActionEvent actionEvent) {
        if (actionEvent.getSource() == show_all_btn)
        {
            cardata.add(new MiniVan("Citroen Xsara MPI 2008", 7800, 4.3, 175, 101));
            cardata.add(new Sedan("Toyota Corolla 2017", 11500, 4.9, 190, 103));
            cardata.add(new Sedan("Shkoda superb 2010", 11000, 5.5, 160, 104));
            cardata.add(new Sedan("Volvo S40 Kinetic 2011", 8950, 4.5, 190, 105));

            id.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
            type.setCellValueFactory(new PropertyValueFactory<Car, String>("type"));
            name.setCellValueFactory(new PropertyValueFactory<Car, String>("name"));
            price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
            consumption.setCellValueFactory(new PropertyValueFactory<Car, String>("consumption"));
            max_velocity.setCellValueFactory(new PropertyValueFactory<Car, String>("max_velocity"));

            cars_table.setItems(cardata);

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
