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
import javafx.scene.text.Font;
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

    private TextField matchId, teamId1, teamId2, gameId, winnerId, date, scoreT1, scoreT2;
    private Button add;
    private Button delete;
    private Button edit;
    private Stage popupWindow;
    private Button addButton,removeButton,editButton,mainMenuButton;
    private HBox buttonBox;
    private Label instructionsForTeamMatches;


    public TeamMatchView(){
        buildUI();
    }

    private void buildUI() {
        teamMatchViewController = new TeamMatchController();
        table = new TableView<TeamMatch>();
        rootTeamMatchScene = new BorderPane();

          buttonBox = new HBox();
          addButton = new Button("Add");
          addButton.setOnAction(this::addButtonPressed);

          removeButton = new Button("Remove");
          removeButton.setOnAction(this::removeButtonPressed);

          editButton = new Button("Edit");
          editButton.setOnAction(this::editButtonPressed);

          mainMenuButton = new Button("Main Menu");

        buttonBox.setSpacing(30);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addButton,removeButton,editButton,mainMenuButton);

        instructionsForTeamMatches = new Label("Klicka på lägg till/ta bort/ändra för att konfigurera din databas, klicka på Huvudmeny eller Tillbaka för att gå tillbaka");

      //  table = new TableView<TeamMatch>();
        table = new TableView<>();

      /*  add = new Button("Add");
        delete = new Button("Delete");
        edit = new Button("Edit");
        rootTeamMatchScene.setPrefSize(800,700);
        table.setPrefSize(200,300);

        buttonBox.getChildren().addAll(add,delete,edit);
        add.setOnAction(this::addButtonPressed);
        add.setPrefSize(50,50);
        delete.setPrefSize(50,50);
        delete.setOnAction(this::removeButtonPressed);
        edit.setPrefSize(50,50);
        edit.setOnAction(this::editButtonPressed);
        buttonBox.setSpacing(15); */

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
        rootTeamMatchScene.setBottom(buttonBox);
        rootTeamMatchScene.setCenter(table);
        rootTeamMatchScene.setTop(instructionsForTeamMatches);

    }

    public BorderPane getRootTeamMatchScene(){
        return rootTeamMatchScene;
    }

    public void addAll(ObservableList<TeamMatch> list){
        table.setItems(list);
    }


    private void editButtonPressed(ActionEvent actionEvent) {
        TeamMatch selectedTeamMatch = table.getSelectionModel().getSelectedItem();
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Edit Match");
        popupWindow.setMinHeight(800);
        popupWindow.setMinWidth(800);

        matchId = new TextField(String.valueOf(selectedTeamMatch.getMatchId()));
        teamId1 = new TextField(String.valueOf(selectedTeamMatch.getTeamId1()));
        teamId2 = new TextField(String.valueOf(selectedTeamMatch.getTeamId2()));
        gameId = new TextField(String.valueOf(selectedTeamMatch.getGameId()));
        winnerId = new TextField(String.valueOf(selectedTeamMatch.getWinnerId()));
        date = new TextField(selectedTeamMatch.getDate());
        scoreT1 = new TextField(String.valueOf(selectedTeamMatch.getScoreT1()));
        scoreT2 = new TextField(String.valueOf(selectedTeamMatch.getScoreT2()));



        Label matchIdLabel = new Label("MatchId: ");
        matchIdLabel.setFont(Font.font("Arial",20));
        matchIdLabel.setAlignment(Pos.CENTER_LEFT);
        Label teamId1Label = new Label("Team Id 1: ");
        Label teamId2Label = new Label("Team Id 2: ");
        Label gameIdLabel = new Label ("Game Id: ");
        Label winnerIdLabel = new Label ("Winner Id: ");
        Label dateLabel = new Label ("Date: ");
        Label scoreT1Label = new Label ("Score Team 1: ");
        Label scoreT2Label = new Label("Score Team 2: ");
        matchId.setPrefWidth(50);
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
        submit.setOnAction(this::changeTeamMatch);

        Label popupLabel = new Label();
        popupLabel.setText("Are you sure?");

        Button noButton = new Button("No");
        noButton.setOnAction(actionEvent1 -> popupWindow.close());

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(actionEvent1 -> popupWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(submit,matchIdLabel,matchId,teamId1Label,teamId1,teamId2Label,teamId2,gameIdLabel,gameId,winnerIdLabel,winnerId,dateLabel,date,scoreT1Label,scoreT1,scoreT2Label,scoreT2);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);

        Scene scene = new Scene (layout);
        popupWindow.setScene(scene);
        popupWindow.show();
    }

    private void removeButtonPressed(ActionEvent actionEvent) {
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Remove Match");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);

        Label popupLabel = new Label();
        popupLabel.setText("Are you sure?");
        Button noButton = new Button("No");
        noButton.setOnAction(e -> popupWindow.close());

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(this::deleteMatch);

        VBox layout = new VBox (10);
        layout.getChildren().addAll(popupLabel, noButton, yesButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene (layout);
        popupWindow.setScene(scene);
        popupWindow.show();

    }

    private void addButtonPressed(ActionEvent actionEvent) {
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add new match");
        popupWindow.setWidth(200);
        popupWindow.setMinHeight(400);
        teamId1 = new TextField();
        teamId2 = new TextField();
        gameId = new TextField();
        winnerId = new TextField();
        date = new TextField();
        scoreT1 = new TextField();
        scoreT2 = new TextField();

        Label teamId1Label = new Label("Team Id 1: ");
        Label teamId2Label = new Label("Team Id 2: ");
        Label gameIdLabel = new Label ("Game Id: ");
        Label winnerIdLabel = new Label ("Winner Id: ");
        Label dateLabel = new Label ("Date: ");
        Label scoreT1Label = new Label ("Score Team 1: ");
        Label scoreT2Label = new Label("Score Team 2: ");

        teamId1.setPromptText("Team 1 Id");
        teamId2.setPromptText("Team 2 Id");
        gameId.setPromptText("Game Id");
        winnerId.setPromptText("Winner Id");
        date.setPromptText("Date");
        scoreT1.setPromptText("Team 1 Score");
        scoreT2.setPromptText("Team 2 Score");

        Button Submit = new Button("Submit");
        Submit.setOnAction(this::addNewTeamMatch);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(Submit,teamId1Label,teamId1,teamId2Label,teamId2,gameIdLabel,gameId,winnerIdLabel,winnerId,dateLabel,date,scoreT1Label,scoreT1,scoreT2Label,scoreT2);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();


    }

    public void addNewTeamMatch(ActionEvent actionEvent) {
        teamMatchViewController.addTeamMatch(Integer.parseInt(teamId1.getText()),Integer.parseInt(teamId2.getText()),Integer.parseInt(gameId.getText()),Integer.parseInt(winnerId.getText()),date.getText(),Integer.parseInt(scoreT1.getText()),Integer.parseInt(scoreT2.getText()));
        popupWindow.close();
    }
    public void changeTeamMatch (ActionEvent actionEvent){
        TeamMatch teamMatch = table.getSelectionModel().getSelectedItem();
        System.out.println(teamMatch.getTeamId1() + " " + teamMatch.getTeamId2());
        teamMatchViewController.changeMatch(Integer.parseInt(matchId.getText()),Integer.parseInt(winnerId.getText()),Integer.parseInt(scoreT1.getText()),Integer.parseInt(scoreT2.getText()));
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