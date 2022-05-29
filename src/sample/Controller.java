package sample;

import java.net.URL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private TextField password;

    @FXML
    private TextField residence_address;

    @FXML
    private TextField registration_address;

    Date date;
    LocalDate dateLD;
    DbHandler dbHandler = new DbHandler();

    @FXML
    void initialize() {

        Reg_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dateLD = birth_date.getValue();
                String formattedDate = dateLD.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                date = Date.valueOf(formattedDate);

                String gender = "";
                if (male.isSelected())
                    gender = "1";
                else
                    gender = "0";
                if(!Name.getText().equals("") && !gender.equals("") && !date.equals("") && !birth_plase.equals("")
                && !registration_address.equals("") && !registration_address.equals("") && !mail.equals("") && !password.equals("")){ // Проверяет введенные даныне на пустоту
                    ResultSet resultCheckUser = dbHandler.checkUser(mail.getText());
                    try {
                        if(resultCheckUser.next()){
                            // IDLABEL.setText("Пользователь существует");
                        }
                        else{
                            dbHandler.register(Name.getText(), gender, date, birth_plase.getText(),
                                    residence_address.getText(), registration_address.getText());
                            ResultSet resultGetID = dbHandler.getUserID(male.getText(), password.getText());
                            try {
                                if(resultGetID.next()){
                                    // authorization.id_employee = resultGetID.getINT(1);
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    // text.setText("Вы что-то не ввели");
                }

            }
        });
    }
}