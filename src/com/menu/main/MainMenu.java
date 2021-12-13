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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.*;

public class MainMenu extends Application implements EventHandler<ActionEvent> {
    private LinkedHashMap<String, MenuCommand> menuItems;
    private LinkedHashMap<String, String> descr;
    private  Scanner scanner = new Scanner(System.in);
    private String s;
    static Stage main_stage;
    static Parent root = null;
    static Parent adding_sc = null;
    static Scene main_scene;
    static Scene addition_scene;

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
    @FXML
    Text price_txt;
    @FXML
    Button add_btn;
    @FXML
    Button add_conf_btn;
    @FXML
    RadioButton type_sedan_rb;
    @FXML
    RadioButton type_sp_rb;
    @FXML
    RadioButton type_mv_rb;
    @FXML
    TextField add_model_txt;
    @FXML
    TextField add_price_txt;
    @FXML
    TextField add_cons_txt;
    @FXML
    TextField add_ms_txt;
    @FXML
    Button delete_btn;
    @FXML
    TextField delete_id_txt;

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
        main_stage = stage;
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        adding_sc = FXMLLoader.load(MainMenu.class.getResource("adding.fxml"));
        main_scene = new Scene(root, 1000, 800);
        addition_scene = new Scene(adding_sc, 1000, 800);
        main_stage.setTitle("Курсова робота Оглашенного Олександра КН-202");
        main_stage.setScene(main_scene);
        main_stage.show();
    }

    @FXML
    public void handle(ActionEvent actionEvent) {

    }

    @FXML
    public void initialize()
    {
        if (root == null)
        {
            add_table();
        }
    }

    private void add_table()
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
        price_txt.setText("" + TP.sum());
    }

    public void click(ActionEvent actionEvent) throws IOException, SQLException {
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
        if (actionEvent.getSource() == add_btn)
        {
            main_stage.setScene(addition_scene);
            main_stage.show();
        }
        if (actionEvent.getSource() == delete_btn)
        {
            TP.removeCar(Integer.parseInt(delete_id_txt.getText()));
        }
        if (actionEvent.getSource() == add_conf_btn)
        {
            Car c = null;

            if (type_sp_rb.isSelected()) {
                c = new Sport(add_model_txt.getText(),
                        Double.parseDouble(add_price_txt.getText()),
                        Double.parseDouble(add_cons_txt.getText()),
                        Double.parseDouble(add_ms_txt.getText()),
                        0);
            }
            else if (type_mv_rb.isSelected()) {
                c = new MiniVan(add_model_txt.getText(),
                        Double.parseDouble(add_price_txt.getText()),
                        Double.parseDouble(add_cons_txt.getText()),
                        Double.parseDouble(add_ms_txt.getText()),
                        0);
            }
            else {
                c = new Sedan(add_model_txt.getText(),
                        Double.parseDouble(add_price_txt.getText()),
                        Double.parseDouble(add_cons_txt.getText()),
                        Double.parseDouble(add_ms_txt.getText()),
                        0);
            }

            TP.addCar(c);

            main_stage.setScene(main_scene);
            main_stage.show();
        }
    }
}
