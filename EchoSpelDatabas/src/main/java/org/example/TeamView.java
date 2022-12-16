package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TeamView {

    private TeamController controller;

    private BorderPane mainPane;
    private TableView<Team> table;
    private Stage popUpWindow;

    private TableColumn<Team, Integer> idColum;
    private TableColumn<Team, String> nameColum;
    private TableColumn<Team, Integer> gameIdColum;

    private TextField teamName, gameId;

    private Button add, delete, edit, homebutton;


    public TeamView(Button button){
        buildUI(button);
    }

    private void buildUI(Button button){
        controller = new TeamController();
        mainPane = new BorderPane();
        mainPane.setPrefSize(800,700);


        //Buttons

        homebutton = button;
        homebutton.setPrefSize(50,50);

        add = new Button("Add");
        add.setPrefSize(50,50);
        add.setOnAction(this::addButtonPressed);


        delete = new Button("Delete");
        delete.setPrefSize(50,50);
        delete.setOnAction(this::deleteButtonPressed);

        edit = new Button("Edit");
        edit.setPrefSize(50,50);
        edit.setOnAction(this::editButtonPressed);






        HBox top = new HBox();
        top.setSpacing(15);
        top.getChildren().addAll(homebutton,add,delete,edit);

        // TableView

        table = new TableView<Team>();
        table.setPrefSize(400,500);

        idColum = new TableColumn<Team,Integer>("Team Id");
        idColum.setCellValueFactory(new PropertyValueFactory<Team,Integer>("teamId"));

        nameColum = new TableColumn<Team,String>("Team Name");
        nameColum.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));

        gameIdColum = new TableColumn<Team,Integer>("Game Id");
        gameIdColum.setCellValueFactory(new PropertyValueFactory<Team, Integer>("gameId"));

        table.getColumns().addAll(idColum,nameColum,gameIdColum);

        table.setFocusTraversable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(controller.getTeamObservableList());


        mainPane.setTop(top);
        mainPane.setCenter(table);






    }




    private void addButtonPressed(ActionEvent event) {
            popUpWindow= new Stage();
            popUpWindow.initModality(Modality.APPLICATION_MODAL);
            popUpWindow.setTitle("Formulär");
            popUpWindow.setMinHeight(400);
            popUpWindow.setMinWidth(300);

            teamName = new TextField();
            teamName.setPromptText("Team Name");
            gameId = new TextField();

            List<String> teamNames = new ArrayList<>();
            ObservableList<Team> test1 = controller.getTeamObservableList();
            Team team;
            int i = 0;
            while(i < test1.size()){
                teamNames.add(( test1.get(i).getName()));
                        i++;
            }
            ChoiceBox<Team> dropDown = new ChoiceBox<>(controller.getTeamObservableList());



            Button Submit = new Button("Sumbit");
            Submit.setOnAction(this::register);

            VBox layout = new VBox(10);
            layout.setSpacing(15);
            layout.getChildren().addAll(teamName,gameId, dropDown,Submit);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            popUpWindow.setScene(scene);
            popUpWindow.show();



    }

    private void register(ActionEvent event) {
    }

    private void deleteButtonPressed(ActionEvent event) {
    }
    private void editButtonPressed(ActionEvent event) {
    }


    public BorderPane getTeamView(){
        return mainPane;
    }

    public void setHomebutton(Button button){
       homebutton = button;

    }



}
