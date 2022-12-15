package org.example;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.tool.schema.Action;
import org.w3c.dom.Text;


// Niclas Larsson
public class PersonalView extends VBox {
    private BorderPane pane;
    private TableView<Personal> table;
    private TableColumn<Personal,String> firstNameColum;
    private TableColumn<Personal, String> lastNameColum;
    private TableColumn<Personal,String>  nickNameColum;
    private TableColumn<Personal, String> adressNameColum;
    private TableColumn<Personal, Integer> postalNumberColum;
    private TableColumn<Personal,String> postalCityColum;
    private TableColumn<Personal, String> countryColum;
    private TableColumn<Personal,String> emailColum;

    private TextField firstName, lastName, nickName, adress, postalNumber, postalCity, country, email;

    private Button add;
    private Button delete;

    private Button edit;

    private HBox top;

    private PersonalController controller;

    private Stage popupWindow;


    public PersonalView(){
        buildUI();
    }




    /* Bygger all Grafik på huvudsidan samt skapar en PersonalController
       Som hämtar all information från databasen och skapar en ObservabelList som fylls med alla Personal objekt
       Tabbellen skapas och kopplas till Personal klassen så att den kan representera objekt av Typen Personal,
       Tabellen får sedan en refference till ObesvabelList i PersonalController  vilket gör att den populerar tabellen med alla
       objekt som finns i listan det medgör att taballen uppdateras automatiskts när något objekt läggs till eller tas bort från ObservabelList.

     */
    private void buildUI(){
        controller = new PersonalController();
        table = new TableView<Personal>();
        pane = new BorderPane();
        add = new Button("Add");
        delete = new Button("Delete");
        edit = new Button("Edit");
        pane.setPrefSize(800,700);
        table.setPrefSize(200,300);

        add.setOnAction(this::addButtonPressed);
        add.setPrefSize(50,50);
        delete.setPrefSize(50,50);
        delete.setOnAction(this::removeButtonPressed);
        edit.setPrefSize(50,50);
        edit.setOnAction(this::editButtonPressed);
        top = new HBox();
        top.setSpacing(15);
        top.getChildren().addAll(add,delete,edit);






        // Skapar Columerna i tabelen samt kopplar varje enskild colum till en property i Person Klassen
        // Så att varje Colum vet vad den ska visa för information
        TableColumn<Personal, String> firstNameColum = new TableColumn<Personal, String>("Firstname");
        firstNameColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("firstName"));

        TableColumn<Personal,String > lastNameColum = new TableColumn<Personal, String>("Lastname");
        lastNameColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("lastName"));

        TableColumn<Personal,String> nickNameColum = new TableColumn<Personal, String>("NickName");
        nickNameColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("nickName"));

        TableColumn<Personal,String> adressNameColum = new TableColumn<Personal, String>("Adress");
        adressNameColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("adress"));

        TableColumn<Personal,Integer> postalNumberColum = new TableColumn<Personal, Integer>("ZipCode");
        postalNumberColum.setCellValueFactory(new PropertyValueFactory<Personal,Integer>("postalNumber"));

         TableColumn <Personal,String> postalCityColum = new TableColumn<Personal, String>("City");
        postalCityColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("postalCity"));

         TableColumn <Personal,String> countryColum = new TableColumn<Personal, String>("Country");
        countryColum.setCellValueFactory(new PropertyValueFactory<Personal,String>("country"));

        TableColumn<Personal,String> emailColum = new TableColumn<Personal, String>("Email");
        emailColum.setCellValueFactory(new PropertyValueFactory<Personal, String>("email"));


        // Lägger ihop alla columer till en tabell samt kopplar tabellen till observabelList så den vet vart den ska leta efter objekt.
        table.getColumns().addAll(firstNameColum,lastNameColum,nickNameColum,adressNameColum,postalNumberColum,postalCityColum,countryColum,emailColum);
        //table.getItems().addAll(controller.getAllPersonal());
        table.setItems(controller.getPersonal1());
        table.setFocusTraversable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        // lägger till allt till root noden.
        pane.setTop(top);
        pane.setCenter(table);
        //getChildren().add(table);
    }

    // retunerar rootnoden , skall användas av Graphics klassen
    public BorderPane getPane(){
        return pane;
    }

    public void addAll(ObservableList<Personal> list ){
        table.setItems(list);
    }

    // Skapar upp popUp rutan med formulär för att lägga till ett nytt Personal objekt
    // Samt skickar vidare till en metod som anropar PersonalController att skapa och lägga till det nya Objektet i databsen
    public void addButtonPressed(ActionEvent event){
         popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Formulär?");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);
         firstName = new TextField();
         lastName = new TextField();
         nickName = new TextField();
         adress = new TextField();
         postalNumber = new TextField();
         postalCity = new TextField();
         country = new TextField();
         email = new TextField();
         firstName.setPromptText("First name");
         lastName.setPromptText("Last name");
         nickName.setPromptText("Nick name");
         adress.setPromptText("Adress");
         postalNumber.setPromptText("ZipCode");
         postalCity.setPromptText("City");
         country.setPromptText("Country");
         email.setPromptText("Email");
         Button Submit = new Button("Sumbit");
         Submit.setOnAction(this::register);

        Label popupLabel = new Label();
        popupLabel.setText("Är du säker?");
        Button nejButton = new Button("NEJ");
        nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

        Button jaButton = new Button("JA");
        jaButton.setOnAction(e -> { popupWindow.close();});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(Submit,firstName,lastName,nickName,adress,postalNumber,postalCity,country,email);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();
    }

    // Skapar popUprutan för att ta bort en person  ifall man klickar ja så skickar den vidare.
    public void removeButtonPressed(ActionEvent event){
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Warning");
        popupWindow.setMinHeight(50);
        popupWindow.setMinWidth(280);
        TextField tf = new TextField();
        tf.setText("Are you sure you want to delete selected item?");
        tf.setEditable(false);
        tf.setPrefHeight(30);
        tf.setPrefWidth(260);


        Button yes = new Button("yes");
        yes.setOnAction(this::remove);
        yes.setPrefSize(50,50);
        Button no = new Button("no");
        no.setPrefSize(50,50);
        no.setOnAction(e -> popupWindow.close());
        Button filler = new Button();
        filler.setPrefSize(13,13);
        filler.setVisible(false);


        HBox box = new HBox();
        box.setPrefSize(100,200);
        box.setSpacing(50);

        box.getChildren().addAll(filler,no,yes);
        BorderPane pane = new BorderPane();

        pane.setBottom(box);
        pane.setCenter(tf);
        Scene scene = new Scene(pane,100,200);
        popupWindow.setScene(scene);
        popupWindow.show();

    }
    // Skickar vidare information som fyllts i registreringsformuläret till PersonalController.
    public void register(ActionEvent e){
        controller.addPersonal(firstName.getText(),lastName.getText(),nickName.getText(),adress.getText(),Integer.parseInt(postalNumber.getText()),postalCity.getText(),country.getText(),email.getText());
        popupWindow.close();
    }

    // anropas från removeButtonPressed
    // Hämtar vilket objekt som är valt från tabellen och skickar vidare  till PersonalController att ta bort detta objekt
    // från databasen
    public void remove(ActionEvent e){
        Personal person = table.getSelectionModel().getSelectedItem();
        if(person != null) {
            try {
                int i = person.getId();
            }catch (NullPointerException a) {
                System.out.println("Test");
            }finally {

            }
            System.out.println(person.getId()+ " " +person.getAdress());
            controller.removePersonal(person);
        }
        popupWindow.close();
    }

    // Skapar popup för att ändra information
    //
    public void editButtonPressed(ActionEvent e){
        Personal pers = table.getSelectionModel().getSelectedItem();
        if(pers != null) {
            popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Formulär?");
            popupWindow.setMinHeight(300);
            popupWindow.setMinWidth(200);
            VBox box = new VBox();
            firstName = new TextField(pers.getFirstName());
            lastName = new TextField(pers.getLastName());
            nickName = new TextField(pers.getNickName());
            adress = new TextField(pers.getAdress());
            postalNumber = new TextField(Integer.toString(pers.getPostalNumber()));
            postalCity = new TextField(pers.getPostalCity());
            country = new TextField(pers.getCountry());
            email = new TextField(pers.getEmail());
            Button submit = new Button("Submit");
            submit.setOnAction(this::uppdate);
            box.getChildren().addAll(firstName,lastName,nickName,adress,postalNumber,postalCity,country,email,submit);
            Scene scene = new Scene(box,200,300);
            popupWindow.setScene(scene);
            popupWindow.show();

        }

    }

    // Hämtar information från  formuläret och skickar vidare det objektet med uppdaterad information till PersonalController för att uppdatera i Databasen.
    public void uppdate(ActionEvent e){
        Personal pers = table.getSelectionModel().getSelectedItem();
        System.out.println(pers.getEmail() + " " + pers.getId());
        pers.uppDatePersonalInfo(firstName.getText(),lastName.getText(),nickName.getText(),adress.getText(),Integer.parseInt(postalNumber.getText()), postalCity.getText(),country.getText(),email.getText());
        controller.updatePersonal(pers);
        popupWindow.close();
    }


}
