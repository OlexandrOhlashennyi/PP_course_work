package com.menu.main;

import Car.Car;
import Car.Sedan;
import Car.Sport;
import Car.MiniVan;
import Product.TaxiPark;
import com.menu.*;

import java.io.IOException;
import java.sql.ResultSet;
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

    public MainMenu() {
        try {
            TP = new TaxiPark("TaxiPark1", "+380994130557", "taxipark1@taxi.com");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        id.setCellValueFactory(new PropertyValueFactory<Car, String>("ID"));
        type.setCellValueFactory(new PropertyValueFactory<Car, String>("type"));
        name.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
        consumption.setCellValueFactory(new PropertyValueFactory<Car, String>("consumption"));
        max_velocity.setCellValueFactory(new PropertyValueFactory<Car, String>("max_velocity"));
    }

    private void printTV(ResultSet rs) throws SQLException {
        Car c = null;
        cardata.clear();
        while (rs.next())
        {
            if (rs.getString("type").contains("sport")) {
                c = new Sport(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
            }
            else if (rs.getString("type").contains("minivan")) {
                c = new MiniVan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
            }
            else {
                c = new Sedan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"));
            }
            cardata.add(c);
        }
        cars_table.setItems(cardata);
    }

    public void click(ActionEvent actionEvent) {
        if (actionEvent.getSource() == show_all_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                ResultSet rs;
                rs = mc.execute(Arrays.asList("all"));
                printTV(rs);
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == show_avail_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                ResultSet rs;
                rs = mc.execute(Arrays.asList());
                printTV(rs);
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == show_betw_btn)
        {
            MenuCommand mc = menuItems.get("show");
            try {
                ResultSet rs;
                double lower = Double.parseDouble(lower_te.getText());
                double upper = Double.parseDouble(upper_te.getText());
                if (speed_rbt.isSelected())
                    rs = mc.execute(Arrays.asList("speed", "" + lower, "" + upper));
                else
                    rs = mc.execute(Arrays.asList("price", "" + lower, "" + upper));
                printTV(rs);
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
