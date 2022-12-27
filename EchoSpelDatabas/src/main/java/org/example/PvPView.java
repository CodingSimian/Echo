package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class PvPView {
    //Observable list är superviktigt, det är den som kopplar sig till tabellen
    //Kompilatorn kan krångla ibland, bara copy pastea in innehåll igen
    private TableView<PvP> mainTable;
    private PlayerController playerController;

    private GameController gameController;

    private PvPController viewController;

    private BorderPane rootPvPScene;
    private Stage popupWindow;
    private Button addButton,removeButton,changeButton,mainMenuButton;

    private TextField match_IdTF, player_Id1TF, player_id2TF, game_IdTF, winner_IdTF, dateTF, score_P1TF, score_P2TF,userTF;
    private Label instructionsForPvP;
    private HBox buttonBox;

    private ChoiceBox viewOptions;
    private ChoiceBox<Player> winnerChoice, playerChoice1,playerchoice2;
    private ChoiceBox<Game> gameChoice;

    private GameController myGameController;
    private PlayerController myPlayerController;

    private ObservableList<Player> playerList;

    private ObservableList<Game> gameList;

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

    mainMenuButton = new Button("HuvudMeny");

    buttonBox.setSpacing(30);
    buttonBox.setAlignment(Pos.CENTER);

    buttonBox.getChildren().addAll(addButton,removeButton,changeButton,mainMenuButton);

    userTF = new TextField();
    userTF.setMinWidth(200);
    userTF.setEditable(false);

    mainTable = new TableView<>();

    TableColumn<PvP, Integer> match_IdColumn = new TableColumn<>("ID of match");
    match_IdColumn.setCellValueFactory(new PropertyValueFactory<>("match_Id")); //lägger in data i cell-tabellen

    TableColumn<PvP, Integer> player_Id2Column = new TableColumn<>("ID player 2");
    player_Id2Column.setCellValueFactory(new PropertyValueFactory<>("player_Id2"));

    TableColumn<PvP, Integer> player_Id1Column = new TableColumn<>("ID-player 1");
    player_Id1Column.setCellValueFactory(new PropertyValueFactory<>("player_Id1"));

    TableColumn<PvP, Integer> game_IdColumn = new TableColumn<>("ID of game being played");
    game_IdColumn.setCellValueFactory(new PropertyValueFactory<>("game_Id"));

    TableColumn<PvP, String> winner_IdColumn = new TableColumn<>("Winning player");
    winner_IdColumn.setCellValueFactory(new PropertyValueFactory<>("winner_Name"));

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
    rootPvPScene.setTop(userTF);
}

public void addButtonPressed(ActionEvent actionEvent){
        popupWindow = new Stage();
    popupWindow.initModality(Modality.APPLICATION_MODAL);
    popupWindow.setTitle("Tilläggsformulär");
    popupWindow.setMinHeight(400);
    popupWindow.setMinWidth(200);

    //Skapa två observableListor, en för alla player objekt och game objekt
    playerList = FXCollections.observableArrayList();
    gameList = FXCollections.observableArrayList();
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    String strQuery = "SELECT c FROM Player c WHERE c.player_Id IS NOT NULL"; //Här är c en placeholder
    TypedQuery<Player> tq = em.createQuery(strQuery, Player.class);

    String strQueryGames ="SELECT c FROM Game c WHERE c.gameId IS NOT NULL";
    TypedQuery<Game> tq2 = em.createQuery(strQueryGames, Game.class);

    try{
        List<Player> somePlayers = tq.getResultList();
        playerList.addAll(somePlayers);

        List<Game> someGames = tq2.getResultList();
        gameList.addAll(someGames);


    }
    catch (NoResultException ex){
        ex.printStackTrace();
    }
    finally{
        em.close();
    }

    playerChoice1 = new ChoiceBox<>();
    playerChoice1.getItems().addAll(playerList);

    playerchoice2 = new ChoiceBox<>();
    playerchoice2.getItems().addAll(playerList);

    gameChoice = new ChoiceBox<>();
    gameChoice.getItems().addAll(gameList);

    //playerChoice1.setItems(myPlayerController.getPlayerList);
    //playerChoice2.setItems(myPlayerController.getPlayerList);
    //gameChoice.setItems(myGameController.getGameList);


    player_Id1TF = new TextField();
    player_Id1TF.setPromptText("First player ID");

    player_id2TF = new TextField();
    player_id2TF.setPromptText("Second player ID");

    game_IdTF = new TextField();
    game_IdTF.setPromptText("ID of the game being played");

    dateTF = new TextField();
    dateTF.setPromptText("Date of the match being played, yyyy-mm-dd");

    Button submitButton = new Button("Submit");
    submitButton.setOnAction(this::register2);

    VBox layout = new VBox(10);
    layout.getChildren().addAll(submitButton,playerChoice1,playerchoice2,gameChoice,dateTF);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    popupWindow.setScene(scene);
    popupWindow.show();
}

    public void removeButtonPressed(ActionEvent actionEvent){
        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Raderingsformulär");
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

    public void changeButtonPressed(ActionEvent actionEvent){
        //Denna metod ska alltså ändra vinnar-id, och poängen för spelare 1 och 2.
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();

        Player player2 = PvPMatchSelected.getPlayer_Id2();
        Player player1 = PvPMatchSelected.getPlayer_Id1();

        winnerChoice = new ChoiceBox<>();
        winnerChoice.getItems().addAll(player2,player1);


        popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Ändringsformulär");
        popupWindow.setMinHeight(400);
        popupWindow.setMinWidth(200);

        winner_IdTF = new TextField(String.valueOf(PvPMatchSelected.getWinner_Name()));
        winner_IdTF.setPromptText("ID of the winner");

        score_P1TF = new TextField(String.valueOf(PvPMatchSelected.getScore_P1()));
        score_P1TF.setPromptText("Score of the first player");

        score_P2TF = new TextField(String.valueOf(PvPMatchSelected.getScore_P2()));
        score_P2TF.setPromptText("Score of the second player");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(this::changeScoreInfo);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(submitButton,winnerChoice,score_P1TF,score_P2TF);
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
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();
        playerController = new PlayerController();

        Player playerForPVPMatch = playerController.returnPlayer(Integer.parseInt(player_Id1TF.getText()));

        Player playerForPVPMatch2 = playerController.returnPlayer(Integer.parseInt(player_id2TF.getText()));

        gameController = new GameController();
        Game gameForPVP = gameController.getGame(Integer.parseInt(game_IdTF.getText()));


        viewController.addPvPMatch( playerForPVPMatch,playerForPVPMatch2,gameForPVP,dateTF.getText());

        //viewController.addPvPMatch(Integer.parseInt(match_IdTF.getText()), Integer.parseInt(player_Id1TF.getText()),Integer.parseInt(player_id2TF.getText()),Integer.parseInt(game_IdTF.getText()),dateTF.getText());
        popupWindow.close();
    }

    public void register2(ActionEvent e){
        //Kom ihåg att göra en returnGame metod imorrn som är returnplayer2


        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();


        Player playerForPVPMatch = playerChoice1.getValue();
        Player playerForPVPMatch2 = playerchoice2.getValue();
        Game gameForPVP = gameChoice.getValue();


        viewController.addPvPMatch(playerForPVPMatch,playerForPVPMatch2,gameForPVP,dateTF.getText());

        //viewController.addPvPMatch(Integer.parseInt(match_IdTF.getText()), Integer.parseInt(player_Id1TF.getText()),Integer.parseInt(player_id2TF.getText()),Integer.parseInt(game_IdTF.getText()),dateTF.getText());
        em.close();
        popupWindow.close();
    }

//OBS register3 är precis som register2 fast här är det som var (int) player_Id1 propertyn för PvP klassen
    //istället det nuvarande (Player) player_1, metoden returnPlayer
    public void changeScoreInfo(ActionEvent e){
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();

        PvPMatchSelected.updateMatch(winnerChoice.getValue().getNickName(),
                Integer.parseInt(score_P1TF.getText()),Integer.parseInt(score_P2TF.getText()));


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

    public Player returnPlayer2(int playerID, EntityManager em){
        String query = "SELECT c FROM Player c WHERE c.player_Id= :player_Id"; //c är placeholder
        TypedQuery<Player> tq = em.createQuery(query, Player.class);
        tq.setParameter("player_Id", playerID);


        Player somePlayer = null;
        try{
            somePlayer = tq.getSingleResult();

        }
        catch(NoResultException ex){
            System.out.println("ex");
            ex.printStackTrace();
        }

        return somePlayer;

    }

    public Game returnGame2(int daGameID, EntityManager em)
    {
        String query = "SELECT c FROM Game c WHERE c.gameId= :game_Id"; //c är placeholder
        TypedQuery<Game> tq = em.createQuery(query, Game.class);
        tq.setParameter("game_Id", daGameID);

        Game someGame = null;
        try{
            someGame = tq.getSingleResult();

        }
        catch(NoResultException ex){
            System.out.println("ex");
            ex.printStackTrace();
        }

        return someGame;

    }
}
