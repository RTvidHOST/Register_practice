package sample;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Name;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton male;

    @FXML
    private Button Reg_button;

    @FXML
    private DatePicker birth_date;

    @FXML
    private TextField birth_plase;

    @FXML
    private TextField mail;

    @FXML
    private TextField residence_address;

    @FXML
    private TextField registration_address;

    @FXML
    void initialize() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        final DbHandler dbHandler = new DbHandler();

        Reg_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                LocalDate localDate = LocalDate.now();
                Date date = Date.valueOf(birth_date.getValue());

                String gender = "";
                if (male.isSelected())
                    gender = "1";
                else
                    gender = "0";

                dbHandler.register(Name.getText(), gender, date, birth_plase.getText(),
                        residence_address.getText(), registration_address.getText());

            }
        });
    }
}