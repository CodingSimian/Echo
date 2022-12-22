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

public class TeamMatchView extends VBox {
    private BorderPane rootTeamMatchScene;
    private TableView<TeamMatch> table;
    private TableColumn<TeamMatch,Integer> matchIdColumn;
    private TableColumn<TeamMatch, Integer> teamId1Column;
    private TableColumn<TeamMatch,Integer>  teamId2Column;
    private TableColumn<TeamMatch, Integer> gameIdColumn;
    private TableColumn<TeamMatch, Integer> winnerIdColumn;
    private TableColumn<TeamMatch,String> dateColumn;
    private TableColumn<TeamMatch, Integer> scoreT1Column;
    private TableColumn<TeamMatch,Integer> scoreT2Column;

    private TeamMatchController teamMatchViewController;
    private TeamController teamController;
    private GameController gameController;

    private TextField matchId, teamId1, teamId2, gameId, winnerId, date, scoreT1, scoreT2;
    private Stage popupWindow;
    private Button addButton,removeButton,editButton,mainMenuButton;
    private HBox buttonBox;
    private Label instructionsForTeamMatches;

    private ChoiceBox <Team> team1ChoiceBox;
    private ChoiceBox<Team> team2ChoiceBox;
    private ChoiceBox<Game> gameChoiceBox;

    ObservableList<Team> teamList = FXCollections.observableArrayList();
    ObservableList<Game> gameObservableList = FXCollections.observableArrayList(); //Behövs för att kunna se lista på Spel

    public TeamMatchView(){
        buildUI();
    }

    private void buildUI() {
        teamMatchViewController = new TeamMatchController();
        teamController = new TeamController();
        gameController = new GameController();
        table = new TableView<>();
        rootTeamMatchScene = new BorderPane();

          buttonBox = new HBox();

          addButton = new Button("Schedule");
          addButton.setPrefSize(100,30);
          addButton.setOnAction(this::addButtonPressed);

          removeButton = new Button("Remove");
          removeButton.setPrefSize(100,30);
          removeButton.setOnAction(this::removeButtonPressed);

          editButton = new Button("Resolve");
          editButton.setPrefSize(100,30);
          editButton.setOnAction(this::editButtonPressed);

          mainMenuButton = new Button("Main Menu");
          mainMenuButton.setPrefSize(100,30);

        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.getChildren().addAll(addButton,removeButton,editButton,mainMenuButton);

        instructionsForTeamMatches = new Label("Press Schedule to schedule a new game. \nPress Resolve to add the final score \nPress Remove to remove a game. ");

        table = new TableView<>();

        TableColumn<TeamMatch, Integer> matchIdColumn = new TableColumn<TeamMatch, Integer>("Match Id");
        matchIdColumn.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("matchId"));
        TableColumn<TeamMatch, Integer> teamId1Column = new TableColumn<TeamMatch, Integer>("Team Id 1");
        teamId1Column.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("teamId1"));
        TableColumn<TeamMatch, Integer> teamId2Column = new TableColumn<TeamMatch, Integer>("Team Id 2");
        teamId2Column.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("teamId2"));
        TableColumn<TeamMatch, Integer> gameIdColumn = new TableColumn<TeamMatch, Integer>("Game Id");
        gameIdColumn.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("gameId"));
        TableColumn<TeamMatch, Integer> winnerIdColumn = new TableColumn<TeamMatch, Integer>("Winner Id");
        winnerIdColumn.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("winnerId"));
        TableColumn<TeamMatch, String> dateColumn = new TableColumn<TeamMatch, String>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<TeamMatch,String>("date"));
        TableColumn<TeamMatch, Integer> scoreT1column = new TableColumn<TeamMatch, Integer>("Score Team 1");
        scoreT1column.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("scoreT1"));
        TableColumn<TeamMatch, Integer> scoreT2Column = new TableColumn<TeamMatch, Integer>("Score Team 2");
        scoreT2Column.setCellValueFactory(new PropertyValueFactory<TeamMatch,Integer>("scoreT2"));

        table.getColumns().addAll(matchIdColumn,teamId1Column,teamId2Column,gameIdColumn,winnerIdColumn,dateColumn,scoreT1column,scoreT2Column);
        table.setItems(teamMatchViewController.getObservableTeamMatch());
        table.setFocusTraversable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        rootTeamMatchScene.setTop(buttonBox);
        rootTeamMatchScene.setCenter(table);
        rootTeamMatchScene.setBottom(instructionsForTeamMatches);
    }

    public BorderPane getRootTeamMatchScene(){
        return rootTeamMatchScene;
    }

    public void addAll(ObservableList<TeamMatch> list){
        table.setItems(list);
    }

    private void addButtonPressed(ActionEvent actionEvent) {
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add new match");
        popupWindow.setWidth(200);
        popupWindow.setMinHeight(400);

        team1ChoiceBox = new ChoiceBox<>(teamList);
        team1ChoiceBox.setItems(teamController.getTeamObservableList());
        team2ChoiceBox = new ChoiceBox<>(teamList);
        team2ChoiceBox.setItems(teamController.getTeamObservableList());
        //  gameChoiceBox = new ChoiceBox<>(gameObservableList);
        //  gameChoiceBox.setItems(gameController.getGameObservableList());

        teamId1 = new TextField();
        teamId2 = new TextField();
        gameId = new TextField();
        date = new TextField();

        Label teamId1Label = new Label("Team Id 1: ");
        Label teamId2Label = new Label("Team Id 2: ");
        Label gameIdLabel = new Label ("Game Id: ");
        Label dateLabel = new Label ("Date: ");

        teamId1.setPromptText("Team 1 Id");
        teamId2.setPromptText("Team 2 Id");
        gameId.setPromptText("Game Id");
        date.setPromptText("Date");


        Button Submit = new Button("Submit");
        Submit.setOnAction(this::addNewTeamMatch);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(Submit,teamId1Label, team1ChoiceBox,teamId2Label, team2ChoiceBox,gameIdLabel,gameId,dateLabel,date);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();

    }

    private void editButtonPressed(ActionEvent actionEvent) {

        try {
        TeamMatch selectedTeamMatch = table.getSelectionModel().getSelectedItem();
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Edit Match");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(400);

        matchId = new TextField(String.valueOf(selectedTeamMatch.getMatchId()));
        teamId1 = new TextField(String.valueOf(selectedTeamMatch.getTeamId1()));
        teamId2 = new TextField(String.valueOf(selectedTeamMatch.getTeamId2()));
        gameId = new TextField(String.valueOf(selectedTeamMatch.getGameId()));
        winnerId = new TextField(String.valueOf(selectedTeamMatch.getWinnerId()));
        date = new TextField(selectedTeamMatch.getDate());
        scoreT1 = new TextField(String.valueOf(selectedTeamMatch.getScoreT1()));
        scoreT2 = new TextField(String.valueOf(selectedTeamMatch.getScoreT2()));

        Label matchIdLabel = new Label("MatchId: ");
        Label teamId1Label = new Label("Team Id 1: ");
        Label teamId2Label = new Label("Team Id 2: ");
        Label gameIdLabel = new Label ("Game Id: ");
        Label winnerIdLabel = new Label ("Winner Id: ");
        Label dateLabel = new Label ("Date: ");
        Label scoreT1Label = new Label ("Score Team 1: ");
        Label scoreT2Label = new Label("Score Team 2: ");

        matchId.setEditable(false);
        teamId1.setEditable(false);
        teamId2.setEditable(false);
        gameId.setEditable(false);
        date.setEditable(false);

        matchId.setPromptText("Match Id");
        teamId1.setPromptText("Team 1 Id");
        teamId2.setPromptText("Team 2 Id");
        gameId.setPromptText("Game Id");
        winnerId.setPromptText("Winner Id");
        date.setPromptText("Date");
        scoreT1.setPromptText("Team 1 Score");
        scoreT2.setPromptText("Team 2 Score");

        Button submit = new Button("Submit");
        submit.setOnAction(this::changeTeamMatch2);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(submit,matchIdLabel,matchId,teamId1Label,teamId1,teamId2Label,teamId2,gameIdLabel,gameId,winnerIdLabel,winnerId,dateLabel,date,scoreT1Label,scoreT1,scoreT2Label,scoreT2);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);

        Scene scene = new Scene (layout);
        popupWindow.setScene(scene);
        popupWindow.show();
        }catch (Exception e){
            e.printStackTrace();
            popupWindow = new Stage();
            popupWindow.setMinHeight(200);
            popupWindow.setMinWidth(200);

            HBox selectAMatch = new HBox();
            selectAMatch.setAlignment(Pos.CENTER);
            selectAMatch.setSpacing(10);

            Button selectAMatchButton = new Button("Please select a match before trying to edit a match.");
            selectAMatchButton.setOnAction(actionEvent1 -> popupWindow.close());
            selectAMatch.getChildren().addAll(selectAMatchButton);

            Scene scene = new Scene (selectAMatch);
            popupWindow.setScene (scene);
            popupWindow.show();
        }
    }

    private void removeButtonPressed(ActionEvent actionEvent) {
        try {
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Remove Match");
        popupWindow.setMinHeight(200);
        popupWindow.setMinWidth(200);

        Label popupLabel = new Label();
        popupLabel.setText("Are you sure?");
        Button noButton = new Button("No");
        noButton.setOnAction(e -> popupWindow.close());

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(this::deleteMatch);

        VBox layout = new VBox (10);
        layout.getChildren().addAll(popupLabel,yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene (layout);
        popupWindow.setScene(scene);
        popupWindow.show();

    }catch (Exception e){
        VBox selectMatch = new VBox();
        Button selectAMatchButton = new Button("You need to select a match to remove it.");
        selectAMatchButton.setOnAction(actionEvent1 -> popupWindow.close());
        selectMatch.setMinHeight(10);
        selectMatch.getChildren().addAll(selectAMatchButton);
        selectMatch.setAlignment(Pos.CENTER);
        selectMatch.setSpacing(10);
        Scene scene = new Scene (selectMatch);
        popupWindow.setScene (scene);
        popupWindow.show();
    }

    }



    public void addNewTeamMatch(ActionEvent actionEvent) {
        teamMatchViewController.addTeamMatch(team1ChoiceBox.getValue().getTeamId(),team2ChoiceBox.getValue().getTeamId(),Integer.parseInt(gameId.getText()),date.getText());
        popupWindow.close();
    }


    public void changeTeamMatch2 (ActionEvent actionEvent) {
        TeamMatch teamMatch = table.getSelectionModel().getSelectedItem();
        teamMatch.changeTeamMatch(Integer.parseInt(matchId.getText()),Integer.parseInt(teamId1.getText()),Integer.parseInt(teamId2.getText()),Integer.parseInt(gameId.getText()),Integer.parseInt(winnerId.getText()),date.getText(),Integer.parseInt(scoreT1.getText()),Integer.parseInt(scoreT2.getText()));
        teamMatchViewController.changeMatch2(teamMatch);
        popupWindow.close();
    }

    public void deleteMatch(ActionEvent actionEvent){
        TeamMatch TeamMatchSelected = table.getSelectionModel().getSelectedItem();
        teamMatchViewController.removeTeamMatch2(TeamMatchSelected);
        popupWindow.close();
    }

    public TableView<TeamMatch> getTable (){
        return table;
    }

    public void setTable (TableView<TeamMatch> table) {
        this.table = table;
    }

   public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public void setMainMenuButton(Button mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

    public void setButtonBox(HBox buttonBox) {
        this.buttonBox = buttonBox;
    }
}

 /*   public void changeTeamMatch (ActionEvent actionEvent){
        TeamMatch teamMatch = table.getSelectionModel().getSelectedItem();
        teamMatchViewController.changeMatch(Integer.parseInt(matchId.getText()),Integer.parseInt(winnerId.getText()),Integer.parseInt(scoreT1.getText()),Integer.parseInt(scoreT2.getText()));
        popupWindow.close();
    } */