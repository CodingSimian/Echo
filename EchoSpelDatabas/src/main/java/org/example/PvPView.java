package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PvPView {
    //Observable list är superviktigt, det är den som kopplar sig till tabellen
    //Kompilatorn kan krångla ibland, bara copy pastea in innehåll igen
    private TableView<PvP> mainTable;
    private PvPController viewController;

    private BorderPane rootPvPScene;
    private Stage popupWindow;
    private Button addButton,removeButton,changeButton,goBackButton,mainMenuButton;

    private TextField match_IdTF, player_Id1TF, player_id2TF, game_IdTF, winner_IdTF, dateTF, score_P1TF, score_P2TF;
    private Label instructionsForPvP;
    private HBox buttonBox;

    public PvPView(){
        BuildUI();
    }
public void BuildUI(){
        viewController = new PvPController(); //PvPControllerns Konstruktor fyller PvPControllerns observable lista med hjälp av en typedquery
        rootPvPScene = new BorderPane();
    buttonBox = new HBox();
    addButton = new Button("Lägg till");
    addButton.setOnAction(this::addButtonPressed);

    removeButton = new Button("Ta bort");
    removeButton.setOnAction(this::removeButtonPressed);

    changeButton = new Button("Ändra");
    changeButton.setOnAction(this::changeButtonPressed);

    goBackButton = new Button("Tillbaka");
    goBackButton.setOnAction(this::backButtonPressed);

    mainMenuButton = new Button("HuvudMeny");

    /*mainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            BorderPane pane = new BorderPane();
            Label label1 = new Label("Test");
            pane.setCenter(label1);
            scene.setRoot(pane);
        }
    });*/


    buttonBox.setSpacing(30);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(addButton,removeButton,changeButton,goBackButton,mainMenuButton);

    instructionsForPvP = new Label("Klicka på lägg till/ta bort/ändra för att konfigurera din databas, klicka på Huvudmeny eller Tillbaka för att gå tillbaka");
    mainTable = new TableView<>();

    TableColumn<PvP, Integer> match_IdColumn = new TableColumn<>("ID of match");
    match_IdColumn.setCellValueFactory(new PropertyValueFactory<>("match_Id")); //lägger in data i cell-tabellen

    TableColumn<PvP, Integer> player_Id2Column = new TableColumn<>("ID of second player");
    player_Id2Column.setCellValueFactory(new PropertyValueFactory<>("player_Id2"));

    TableColumn<PvP, Integer> player_Id1Column = new TableColumn<>("ID of first player");
    player_Id1Column.setCellValueFactory(new PropertyValueFactory<>("player_Id1"));

    TableColumn<PvP, Integer> game_IdColumn = new TableColumn<>("ID of game being played");
    game_IdColumn.setCellValueFactory(new PropertyValueFactory<>("game_Id"));

    TableColumn<PvP, Integer> winner_IdColumn = new TableColumn<>("ID of the player that won");
    winner_IdColumn.setCellValueFactory(new PropertyValueFactory<>("winner_Id"));

    TableColumn<PvP,Integer> score_P2Column = new TableColumn<>("Score of the second player");
    score_P2Column.setCellValueFactory(new PropertyValueFactory<PvP,Integer>("score_P2"));

    TableColumn<PvP, Integer> score_P1Column = new TableColumn<>("Score of the first player");
    score_P1Column.setCellValueFactory(new PropertyValueFactory<PvP,Integer>("score_P1"));

    TableColumn<PvP,String> dateColumn = new TableColumn<>("Date of match being played");
    dateColumn.setCellValueFactory(new PropertyValueFactory<PvP,String>("date"));

    mainTable.getColumns().addAll(match_IdColumn,player_Id2Column,player_Id1Column,game_IdColumn,winner_IdColumn,score_P1Column,score_P2Column,dateColumn);
    mainTable.setItems(viewController.getPvPList());

    mainTable.setFocusTraversable(false); //Venne vad detta gör men Niclas skrev detta i sin klass så antar(?) det är bra
    mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //Venne vad denna gör heller lmao
    rootPvPScene.setBottom(buttonBox);
    rootPvPScene.setCenter(mainTable);
    rootPvPScene.setTop(instructionsForPvP);
}

public void addButtonPressed(ActionEvent actionEvent){
        popupWindow = new Stage();
    popupWindow.initModality(Modality.APPLICATION_MODAL);
    popupWindow.setTitle("Tilläggsformulär");
    popupWindow.setMinHeight(400);
    popupWindow.setMinWidth(200);

    match_IdTF = new TextField();
    match_IdTF.setPromptText("Match ID");

    player_Id1TF = new TextField();
    player_Id1TF.setPromptText("First player ID");

    player_id2TF = new TextField();
    player_id2TF.setPromptText("Second player ID");

    game_IdTF = new TextField();
    game_IdTF.setPromptText("ID of the game being played");

    dateTF = new TextField();
    dateTF.setPromptText("Date of the match being played, yyyy-mm-dd");

    Button submitButton = new Button("Submit");
    submitButton.setOnAction(this::register);

    Label popupLabel = new Label();
    popupLabel.setText("Är du säker?");
    Button nejButton = new Button("NEJ");
    nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

    Button jaButton = new Button("JA");
    jaButton.setOnAction(e -> { popupWindow.close();});

    VBox layout = new VBox(10);
    layout.getChildren().addAll(submitButton,match_IdTF,player_Id1TF,player_id2TF,game_IdTF,dateTF);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    popupWindow.setScene(scene);
    popupWindow.show();
}

    public void removeButtonPressed(ActionEvent actionEvent){
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Tilläggsformulär");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);

        Label popupLabel = new Label();
        popupLabel.setText("Är du säker?");
        Button nejButton = new Button("NEJ");
        nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

        Button jaButton = new Button("JA");
        jaButton.setOnAction(this::deleteMatch);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(popupLabel,nejButton,jaButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();

    }

    public void backButtonPressed(ActionEvent actionEvent){
        System.out.println("Hullo");
    }

    /*public void mainMenuButtonPressed(ActionEvent actionEvent){
        System.out.println("Hullo");
    }*/

    public void changeButtonPressed(ActionEvent actionEvent){
        //Denna metod ska alltså ändra vinnar-id, och poängen för spelare 1 och 2.
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Tilläggsformulär");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);

        player_Id1TF = new TextField(String.valueOf(PvPMatchSelected.getPlayer_Id1()));
        player_Id1TF.setPromptText("First player ID");

        player_id2TF = new TextField(String.valueOf(PvPMatchSelected.getPlayer_Id2()));
        player_id2TF.setPromptText("Second player ID");

        game_IdTF = new TextField(String.valueOf(PvPMatchSelected.getGame_Id()));
        game_IdTF.setPromptText("ID of the game being played");


        winner_IdTF = new TextField(String.valueOf(PvPMatchSelected.getWinner_Id()));
        winner_IdTF.setPromptText("ID of the winner");

        dateTF = new TextField(PvPMatchSelected.getDate());
        dateTF.setPromptText("Date of the match being played, yyyy-mm-dd");

        score_P1TF = new TextField(String.valueOf(PvPMatchSelected.getScore_P1()));
        score_P1TF.setPromptText("Score of the first player");

        score_P2TF = new TextField(String.valueOf(PvPMatchSelected.getScore_P2()));
        score_P2TF.setPromptText("Score of the second player");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(this::register2);


        Label popupLabel = new Label();
        popupLabel.setText("Är du säker?");
        Button nejButton = new Button("NEJ");
        nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

        Button jaButton = new Button("JA");
        jaButton.setOnAction(e -> { popupWindow.close();});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(submitButton,player_Id1TF,player_id2TF,game_IdTF,dateTF,winner_IdTF,score_P1TF,score_P2TF);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.show();

    }

    public TableView<PvP> getMainTable() {
        return mainTable;
    }

    public void setMainTable(TableView<PvP> mainTable) {
        this.mainTable = mainTable;
    }

    public PvPController getViewController() {
        return viewController;
    }

    public void setViewController(PvPController viewController) {
        this.viewController = viewController;
    }
    public void register(ActionEvent e){
        viewController.addPvPMatch(Integer.parseInt(match_IdTF.getText()), Integer.parseInt(player_Id1TF.getText()),Integer.parseInt(player_id2TF.getText()),Integer.parseInt(game_IdTF.getText()),dateTF.getText());
        popupWindow.close();
    }

    public void register2(ActionEvent e){
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();

        PvPMatchSelected.updateMatch(Integer.parseInt(player_Id1TF.getText()),
                Integer.parseInt(player_id2TF.getText()),Integer.parseInt(game_IdTF.getText()),Integer.parseInt(winner_IdTF.getText()),
                dateTF.getText(),Integer.parseInt(score_P1TF.getText()),Integer.parseInt(score_P2TF.getText()));

        viewController.updateInfoPvPMatch(PvPMatchSelected);

        popupWindow.close();
    }

    public void deleteMatch(ActionEvent e){
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();
        viewController.deleteMatch2(PvPMatchSelected);
        popupWindow.close();
    }

    public BorderPane getRootPvPScene() {
        return rootPvPScene;
    }

    public void setRootPvPScene(BorderPane rootPvPScene) {
        this.rootPvPScene = rootPvPScene;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public void setChangeButton(Button changeButton) {
        this.changeButton = changeButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public void setGoBackButton(Button goBackButton) {
        this.goBackButton = goBackButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public void setMainMenuButton(Button mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }

    public Label getInstructionsForPvP() {
        return instructionsForPvP;
    }

    public void setInstructionsForPvP(Label instructionsForPvP) {
        this.instructionsForPvP = instructionsForPvP;
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

    public void setButtonBox(HBox buttonBox) {
        this.buttonBox = buttonBox;
    }
}
