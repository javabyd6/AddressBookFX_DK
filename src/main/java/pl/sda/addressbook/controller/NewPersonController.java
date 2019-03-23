package pl.sda.addressbook.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.sda.addressbook.Main;
import pl.sda.addressbook.model.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPersonController implements Initializable {

    @FXML
    private TextField getName;
    @FXML
    private TextField getLastName;
    @FXML
    private TextField getCiy;
    @FXML
    private TextField getAddress;
    @FXML
    private TextField getTelephone;
    @FXML
    private TextField getPostalCode;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public void setSaveButton(){
        Person person= new Person(getName.getText(),getLastName.getText(),getCiy.getText(),getPostalCode.getText(),getTelephone.getText(),getAddress.getText());
      main.getPersonList().add(person);
      Stage stage= (Stage) saveButton.getScene().getWindow();
      stage.close();
    }

    @FXML
    public void exitApplication() {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
