package org.example;

import javafx.collections.FXCollections;
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
    private TableColumn<Team, Game> gameIdColum;

    private TextField teamName;


    private Button add, delete, edit, homebutton, addTeamMate, deleteTeamMate;


    private ChoiceBox<Player> membersDropDown;
    private ChoiceBox<Player> othersDropDown;

    private ChoiceBox<Game> gamesDropDown;

    ObservableList<Player> teamMembers = FXCollections.observableArrayList();
    ObservableList<Player> allPlayers = FXCollections.observableArrayList();
    ObservableList<Player>  others = FXCollections.observableArrayList();
    ObservableList<Game> games = FXCollections.observableArrayList();

    List<Player> alterdPlayers = new ArrayList<>();


    public TeamView(){
        buildUI();
    }

    private void buildUI(){
        controller = new TeamController();
        mainPane = new BorderPane();
        mainPane.setPrefSize(800,700);

        games.addAll(controller.getAllGames());
        allPlayers.addAll(controller.getAllPlayers());



        //Buttons

        homebutton = new Button("Home");
        homebutton.setPrefSize(75,50);

        add = new Button("Add");
        add.setPrefSize(75,50);
        add.setOnAction(this::addButtonPressed);


        delete = new Button("Delete");
        delete.setPrefSize(75,50);
        delete.setOnAction(this::deleteButtonPressed);

        edit = new Button("Edit");
        edit.setPrefSize(75,50);
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

        gameIdColum = new TableColumn<Team,Game>("Game");
        gameIdColum.setCellValueFactory(new PropertyValueFactory<Team, Game>("game"));


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

            gamesDropDown = new ChoiceBox<>(games);

            Button Submit = new Button("Sumbit");
            Submit.setOnAction(this::register);

            VBox layout = new VBox(10);
            layout.setSpacing(15);
            layout.getChildren().addAll(teamName, gamesDropDown,Submit);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            popUpWindow.setScene(scene);
            popUpWindow.show();



    }

    private void upDate(ActionEvent event) {
        table.getSelectionModel().getSelectedItem().setGame(gamesDropDown.getValue());
        table.getSelectionModel().getSelectedItem().setName(teamName.getText());
        controller.updateTeam(table.getSelectionModel().getSelectedItem());
        controller.updatePlayers(alterdPlayers);
        popUpWindow.close();

    }
    private void register(ActionEvent event){
        controller.addTeam(teamName.getText(),gamesDropDown.getSelectionModel().getSelectedItem());
        popUpWindow.close();
    }

    private void deleteButtonPressed(ActionEvent event) {
       if(table.getSelectionModel().getSelectedItem() != null) {
           popUpWindow = new Stage();
           popUpWindow.initModality(Modality.APPLICATION_MODAL);
           popUpWindow.setTitle("Warning");
           popUpWindow.setMinHeight(50);
           popUpWindow.setMinWidth(280);
           TextField tf = new TextField();
           tf.setText("Are you sure you want to delete selected item?");
           tf.setEditable(false);
           tf.setPrefHeight(30);
           tf.setPrefWidth(260);

           Button yes = new Button("yes");
           yes.setOnAction(this::remove);
           yes.setPrefSize(50, 50);
           Button no = new Button("no");
           no.setPrefSize(50, 50);
           no.setOnAction(e -> popUpWindow.close());
           Button filler = new Button();
           filler.setPrefSize(13, 13);
           filler.setVisible(false);

           HBox box = new HBox();
           box.setPrefSize(100, 200);
           box.setSpacing(50);

           box.getChildren().addAll(filler, no, yes);
           BorderPane pane = new BorderPane();

           pane.setBottom(box);
           pane.setCenter(tf);
           Scene scene = new Scene(pane, 100, 200);
           popUpWindow.setScene(scene);
           popUpWindow.show();
       }

    }

    public void remove(ActionEvent event){
        controller.removeTeam(table.getSelectionModel().getSelectedItem());
        popUpWindow.close();
    }
    private void editButtonPressed(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            popUpWindow = new Stage();
            popUpWindow.initModality(Modality.APPLICATION_MODAL);
            popUpWindow.setTitle("Formulär");
            popUpWindow.setMinHeight(400);
            popUpWindow.setMinWidth(300);

            teamName = new TextField(table.getSelectionModel().getSelectedItem().getName());
            teamName.setPromptText("Team Name");

            HBox buttonBox = new HBox();
            buttonBox.setSpacing(50);
            buttonBox.setAlignment(Pos.CENTER);

            addTeamMate = new Button("Add");
            addTeamMate.setOnAction(this::addTeamMateButtonPressed);
            addTeamMate.setPrefWidth(75);

            deleteTeamMate = new Button("Delete");
            deleteTeamMate.setOnAction(this::deleteTeamMateButtonPressed);
            deleteTeamMate.setPrefWidth(75);

            buttonBox.getChildren().addAll(deleteTeamMate, addTeamMate);

            HBox box = new HBox();
            box.setSpacing(50);
            box.setAlignment(Pos.CENTER);
            Label label1 = new Label("Team Members");
            Label label2 = new Label("Other Players");
            Label label3 = new Label("Game");
            box.getChildren().addAll(label1, label2);

            // ChoiceBoxes
            alterdPlayers.clear();
            others.clear();
            others.addAll(allPlayers);
            othersDropDown = new ChoiceBox<>(others);
            othersDropDown.setPrefWidth(75);
            List<Player> toRemove = table.getSelectionModel().getSelectedItem().getTeamMembers();
            Player player1;

            for (Player player : toRemove) {
                player1 = player;
                for (int j = 0; j<others.size(); j++ ) {
                    if (others.get(j).getPlayer_Id() == player1.getPlayer_Id()) {
                        others.remove(j);
                    }
                }

            }

            teamMembers.clear();
            teamMembers.addAll(table.getSelectionModel().getSelectedItem().getTeamMembers());
            membersDropDown = new ChoiceBox<>(teamMembers);
            membersDropDown.setPrefWidth(75);
            others.removeAll(teamMembers);
            gamesDropDown = new ChoiceBox<>(games);
            // Sets correct starting value for gamesDropDown
            Game game1;
            int value = 0;
            for(int i = 0; i<games.size(); i++){
                if(table.getSelectionModel().getSelectedItem().getGame() == games.get(i)){
                    value = i;
                }

            }
            gamesDropDown.setValue(games.get(value));
            gamesDropDown.setPrefWidth(75);


            // Hbox for dropDowns Controlling members
            HBox box2 = new HBox();
            box2.setSpacing(50);
            box2.setAlignment(Pos.CENTER);
            box2.getChildren().addAll(membersDropDown, othersDropDown);


            Button Submit = new Button("Sumbit");
            Submit.setOnAction(this::upDate);

            VBox layout = new VBox(10);
            layout.setSpacing(30);

            layout.getChildren().addAll(teamName, label3, gamesDropDown, box, box2, buttonBox, Submit);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            popUpWindow.setScene(scene);
            popUpWindow.show();
        }
    }

    public void addTeamMateButtonPressed(ActionEvent event){
            othersDropDown.getValue().setTeam_Id(table.getSelectionModel().getSelectedItem().getTeamId());
            alterdPlayers.add(othersDropDown.getValue());
            teamMembers.add(othersDropDown.getValue());
            others.remove(othersDropDown.getValue());


    }

    public void deleteTeamMateButtonPressed(ActionEvent event){
        membersDropDown.getValue().setTeam_IdNull();
        alterdPlayers.add(membersDropDown.getValue());
        others.add(membersDropDown.getValue());
        teamMembers.remove(membersDropDown.getValue());

    }
    public BorderPane getTeamView(){
        return mainPane;
    }

    public Button getHomebutton(){
        return  homebutton;
    }
}
