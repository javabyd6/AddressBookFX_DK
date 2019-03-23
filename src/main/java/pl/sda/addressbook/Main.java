package pl.sda.addressbook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sda.addressbook.controller.RootViewController;
import pl.sda.addressbook.model.Person;

public class Main extends Application {

    private  ObservableList<Person> personList = FXCollections.observableArrayList();

    public Main() {
        personList.add(new Person("Jan","Kowalski","Bydgoszcz","85439","883983345","adres"));
        personList.add(new Person("Dawid","Kamasz","Bydgoszcz","25439","483983345","hehehe"));
    }

    public  ObservableList<Person> getPersonList(){
        return personList;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      //  Parent root = FXMLLoader.load(getClass().getResource("/root.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/root.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        RootViewController rootViewController= loader.getController();
        rootViewController.setMain(this);
        rootViewController.loadPerson();



        primaryStage.setScene(new Scene(root,650,400));
        primaryStage.show();
    }


}
