package org.example;

import javafx.collections.FXCollections;
import javafx.scene.layout.Border;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.tool.schema.Action;
import org.w3c.dom.Text;
import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class GameView extends VBox {

    private BorderPane pane; // my body
    private TableView<Game> table;
    private TableColumn<Game, String> gameNameColumn;
    private TextField gameName;
    private Button add;
    private Button delete;
    private Button edit;
    private Button homeButton;
    private HBox top;
    private GameController controller;
    private Stage popupWindow;

    ObservableList<Game> gameObservableList = FXCollections.observableArrayList();

    GameView(Button button) {
        buildUI(button);

    }

    private void buildUI(Button button){
        controller = new GameController();
        table = new TableView<Game>();
        pane = new BorderPane();
        add = new Button("Add");
        delete = new Button("Delete");
        edit = new Button("Edit");
        homeButton = button;
        pane.setPrefSize(800,700);
        table.setPrefSize(200,300);
        homeButton.setPrefSize(100,25);
        add.setOnAction(this::addButtonPressed);
        add.setPrefSize(100,25);
        delete.setPrefSize(100,25);
        delete.setOnAction(this::removeButtonPressed);
        edit.setPrefSize(100,25);
        edit.setOnAction(this::editButtonPressed);
        top = new HBox();
        top.setSpacing(15);
        top.getChildren().addAll(homeButton,add,delete,edit);


        gameNameColumn = new TableColumn<Game, String>("Game");
        gameNameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("gameName"));


        TableColumn<Game,String> gameNameColumn = new TableColumn<Game, String>("Game Name");
        gameNameColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("gameName"));


        table.getColumns().addAll(gameNameColumn);
        table.getItems().addAll(controller.getGameObservableList());
        table.setItems(controller.getGameObservableList());
        table.setFocusTraversable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        // MAKES IT VISIBLE
        pane.setTop(top);
  //    pane.setStyle("-fx-background-color: #777473");
        pane.setCenter(table);
  //      table.setStyle("-fx-background-color: #ffffff");
  //      gameNameColumn.setStyle("-fx-background-color: #aaaaaa"); //background for whole table
  //      homeButton.setStyle("-fx-background-color: #a72608");
  //      add.setStyle("-fx-background-color: #a72608");
  //      delete.setStyle("-fx-background-color: #a72608");
  //      edit.setStyle("-fx-background-color: #a72608");

    }
    public void setHomeButton(Button button){
        homeButton = button;

    }


    public BorderPane getPane(){

        return pane;
    }

    public void addAll(ObservableList<Game> gameObservableList ){
        table.setItems(gameObservableList);
    }

    public void addButtonPressed(ActionEvent event){

        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Formulär?");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);
        gameName = new TextField();
        gameName.setPromptText("Game name");
        Button Submit = new Button("Sumbit");
        Submit.setOnAction(this::register);

        Label popupLabel = new Label();
        popupLabel.setText("Är du säker?");
        Button nejButton = new Button("NEJ");
        nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

        Button jaButton = new Button("JA");
        jaButton.setOnAction(e -> { popupWindow.close();});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(Submit,gameName);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();
    }

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

    public void register(ActionEvent e){
        controller.addGame(gameName.getText());
        popupWindow.close();
    }


    public void remove(ActionEvent e){
        Game game = table.getSelectionModel().getSelectedItem();
            System.out.println(game.getGameName());
            controller.removeGame(game); //Viktors metod
        popupWindow.close();
    }

    public void editButtonPressed(ActionEvent e){
        Game game = table.getSelectionModel().getSelectedItem();
        if(game != null) {
            popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Formulär?");
            popupWindow.setMinHeight(300);
            popupWindow.setMinWidth(200);
            VBox box = new VBox();
            gameName = new TextField(game.getGameName());
            Button submit = new Button("Submit");
            submit.setOnAction(this::uppdate);
            box.getChildren().addAll(gameName,submit);
            Scene scene = new Scene(box,200,300);
            popupWindow.setScene(scene);
            popupWindow.show();

        }

    }

    // Hämtar information från  formuläret och skickar vidare det objektet med uppdaterad information till PersonalController för att uppdatera i Databasen.
    public void uppdate(ActionEvent e){
        table.getSelectionModel().getSelectedItem().updateGame(gameName.getText());
        controller.updateGame(table.getSelectionModel().getSelectedItem());
        popupWindow.close();
    }

    public Button getHomeButton() {
        return homeButton;
    }
}

