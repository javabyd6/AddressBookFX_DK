package pl.sda.addressbook.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.sda.addressbook.Main;
import pl.sda.addressbook.csvparser.Parser;
import pl.sda.addressbook.csvparser.RealEstate;
import pl.sda.addressbook.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RootViewController implements Initializable {

    @FXML
    private TableView<Person> personTableView;
    @FXML
    private TableColumn<Person,String> nameCol;
    @FXML
    private TableColumn<Person,String> lastNameCol;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Button newPersonButton;
    @FXML
    private Button editButton;
    @FXML
    private Button saveCSV;
    @FXML
    private Button saveButton;


    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadPerson() {
        System.out.println(getMain().getPersonList());
        personTableView.setItems(getMain().getPersonList());
        nameCol.setCellValueFactory(c->c.getValue().nameProperty());
        lastNameCol.setCellValueFactory(c->c.getValue().lastnameProperty());
    }

    public void selectedPerson(MouseEvent mouseEvent) {
        Person person = personTableView.getSelectionModel().getSelectedItem();
        nameLabel.setText(person.getName());
        lastNameLabel.setText(person.getLastname());
        addressLabel.setText(person.getAddress());
        cityLabel.setText(person.getCity());
        postalCodeLabel.setText(person.getPostalcode());
        telephoneLabel.setText(person.getTelephone());
    }



    public void setNewPersonButton() throws IOException {
        Stage stage= new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/newperson.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        NewPersonController newPersonController= loader.getController();
        newPersonController.setMain(main);

        stage.setScene(new Scene(root,650,400));
        stage.show();
    }

    public List<String> getAllAddress(){
        Parser parser = new Parser();
        List<RealEstate> realEstateList = new ArrayList<>();
        try {
           realEstateList = parser.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> addressList = new ArrayList<>();
        for (RealEstate realEstate : realEstateList) {
            addressList.add(realEstate.getStreet());
        }
        return addressList;
    }
    public void setDeleteButtonButton(){
        List<String> address = new ArrayList<>();
        address.addAll(getAllAddress());
        for (String s : address) {
            Person person = new Person("csv","","","","",s);
            main.getPersonList().add(person);
        }
    }

    }

