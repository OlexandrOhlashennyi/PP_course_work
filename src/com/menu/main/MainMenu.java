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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.*;

public class MainMenu extends Application implements EventHandler<ActionEvent> {
    private LinkedHashMap<String, MenuCommand> menuItems;
    static int last_id = 0;
    static Stage main_stage;
    static Parent root = null;
    static Parent adding_sc = null;
    static Parent price_sc = null;
    static Scene main_scene;
    static Scene addition_scene;
    static Scene price_scene;

    TableColumn id = new TableColumn("ID");
    TableColumn type = new TableColumn("type");
    TableColumn name = new TableColumn("name");
    TableColumn price = new TableColumn("price");
    TableColumn consumption = new TableColumn("consumption");
    TableColumn max_velocity = new TableColumn("max_velocity");
    TableColumn busy = new TableColumn("busy");

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
    @FXML
    Button order_btn;
    @FXML
    Button unorder_btn;
    @FXML
    Button cancel_btn;
    @FXML
    Button exit_btn;
    @FXML
    Button price_btn;
    @FXML
    RadioButton mode_norm_rb;
    @FXML
    RadioButton mode_cg_rb;
    @FXML
    RadioButton mode_ex_rb;
    @FXML
    Button pr_cancel_btn;
    @FXML
    Button calc_btn;
    @FXML
    Text car_price_txt;
    @FXML
    TextField length_txt;
    @FXML
    Button ch_mode_btn;
    @FXML
    Pane mode_ch_pane;

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
        menuItems = new LinkedHashMap<>();
        menuItems.put(Add.NAME, new Add(TP));
        menuItems.put(Delete.NAME, new Delete(TP));
        menuItems.put(Order.NAME, new Order(TP));
        menuItems.put(Agr.NAME, new Agr(TP));
        menuItems.put(Show.NAME, new Show(TP));
        menuItems.put(Exit.NAME, new Exit(TP));
    }

    public MainMenu(TaxiPark TP)
    {
        menuItems = new LinkedHashMap<>();
        menuItems.put(Add.NAME, new Add(TP));
        menuItems.put(Delete.NAME, new Delete(TP));
        menuItems.put(Order.NAME, new Order(TP));
        menuItems.put(Agr.NAME, new Agr(TP));
        menuItems.put(Show.NAME, new Show(TP));
        menuItems.put(Exit.NAME, new Exit(TP));
    }

    public void execute() throws InterruptedException, IOException, SQLException {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        main_stage = stage;
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        adding_sc = FXMLLoader.load(MainMenu.class.getResource("adding.fxml"));
        price_sc = FXMLLoader.load(MainMenu.class.getResource("price.fxml"));
        main_scene = new Scene(root, 1000, 800);
        addition_scene = new Scene(adding_sc, 1000, 800);
        price_scene = new Scene(price_sc, 1000, 800);
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
        cars_table.getColumns().addAll(id,type,name,price,consumption,max_velocity,busy);
        id.setCellValueFactory(new PropertyValueFactory<Car, String>("ID"));
        type.setCellValueFactory(new PropertyValueFactory<Car, String>("type"));
        name.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
        consumption.setCellValueFactory(new PropertyValueFactory<Car, String>("consumption"));
        max_velocity.setCellValueFactory(new PropertyValueFactory<Car, String>("max_velocity"));
        busy.setCellValueFactory(new PropertyValueFactory<Car, String>("busy"));

        name.setPrefWidth(400);
        price.setPrefWidth(100);
        consumption.setPrefWidth(100);
        busy.setPrefWidth(58);
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
                        rs.getInt("ID"),
                        rs.getBoolean("busy"));
            }
            else if (rs.getString("type").contains("minivan")) {
                c = new MiniVan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"),
                        rs.getBoolean("busy"));
            }
            else {
                c = new Sedan(rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getFloat("consumption"),
                        rs.getFloat("max_velocity"),
                        rs.getInt("ID"),
                        rs.getBoolean("busy"));
            }
            cardata.add(c);
        }
        cars_table.setItems(cardata);
        price_txt.setText("" + TP.sum());
    }

    public void click(ActionEvent actionEvent) throws IOException, SQLException, InterruptedException {
        if (actionEvent.getSource() == show_all_btn)
        {
            MenuCommand mc = menuItems.get("show");
            ResultSet rs;
            rs = mc.execute(Arrays.asList("all"));
            printTV(rs);
        }
        if (actionEvent.getSource() == show_avail_btn)
        {
            MenuCommand mc = menuItems.get("show");
            ResultSet rs;
            rs = mc.execute(Arrays.asList());
            printTV(rs);
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
            MenuCommand mc = menuItems.get("delete");
            mc.execute(Arrays.asList(delete_id_txt.getText()));
        }
        if (actionEvent.getSource() == order_btn)
        {
            MenuCommand mc = menuItems.get("order");
            mc.execute(Arrays.asList("order", delete_id_txt.getText()));
        }
        if (actionEvent.getSource() == unorder_btn)
        {
            MenuCommand mc = menuItems.get("order");
            mc.execute(Arrays.asList("unorder", delete_id_txt.getText()));
        }
        if (actionEvent.getSource() == add_conf_btn)
        {
            MenuCommand mc = menuItems.get("add");
            String type;
            if (type_mv_rb.isSelected())
                type = "minivan";
            else if (type_sedan_rb.isSelected())
                type = "sedan";
            else
                type = "sport";

            mc.execute(Arrays.asList(type, add_model_txt.getText(), add_price_txt.getText(), add_cons_txt.getText(), add_ms_txt.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (actionEvent.getSource() == pr_cancel_btn)
        {
            mode_ch_pane.setVisible(false);
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (actionEvent.getSource() == cancel_btn)
        {
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (actionEvent.getSource() == exit_btn)
        {
            MenuCommand mc = menuItems.get("exit");
            mc.execute(Arrays.asList());
        }
        if (actionEvent.getSource() == price_btn)
        {
            last_id = Integer.parseInt(delete_id_txt.getText());
            main_stage.setScene(price_scene);
            main_stage.show();
        }
        if (actionEvent.getSource() == calc_btn)
        {
            int m = 1;
            if (mode_cg_rb.isSelected())
                m = 2;
            if  (mode_ex_rb.isSelected())
                m = 3;
            car_price_txt.setText(String.format("%.2f", TP.cost(last_id, Double.parseDouble(length_txt.getText()), m)));
        }
        if (actionEvent.getSource() == ch_mode_btn)
        {
            String type = null;
            try {
                type = TP.getType(last_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            mode_norm_rb.setSelected(true);
            mode_cg_rb.setSelected(false);
            mode_ex_rb.setSelected(false);

            mode_ex_rb.setVisible(false);
            if (type.contains("sport"))
                mode_ex_rb.setVisible(true);

            mode_cg_rb.setVisible(false);
            if (type.contains("minivan"))
                mode_cg_rb.setVisible(true);

            mode_ch_pane.setVisible(true);
        }
    }
}
