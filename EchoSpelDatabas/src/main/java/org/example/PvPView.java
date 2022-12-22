package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
    private Button addButton,removeButton,changeButton,goBackButton,mainMenuButton;

    private TextField match_IdTF, player_Id1TF, player_id2TF, game_IdTF, winner_IdTF, dateTF, score_P1TF, score_P2TF,userTF;
    private Label instructionsForPvP;
    private HBox buttonBox;

    private ChoiceBox viewOptions;

    public PvPView(){
        BuildUI();
    }
public void BuildUI(){
        viewController = new PvPController(); //PvPControllerns Konstruktor fyller PvPControllerns observable lista med hjälp av en typedquery
        rootPvPScene = new BorderPane();

    String viewOptionsList[] = {"Normal-Vy","Smeknamns-Vy"};

    viewOptions = new ChoiceBox(FXCollections.observableArrayList(viewOptionsList));

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

    buttonBox.setSpacing(30);
    buttonBox.setAlignment(Pos.CENTER);

    buttonBox.getChildren().addAll(addButton,removeButton,changeButton,goBackButton,mainMenuButton,viewOptions);

    userTF = new TextField();
    userTF.setMinWidth(200);
    userTF.setEditable(false);

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

    viewOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override//Numbers här är indexes
        public void changed(ObservableValue<? extends Number> observableValue, Number value, Number new_value) {
            String myChoice = viewOptionsList[new_value.intValue()];

            if(myChoice.equals("Smeknamns-Vy")){
                //Kod här inne ska alltså ändra på tabellen, så att de kolumner som visar player-id istället visar
                //De nicknames som är länkade till de player-ids.


            } else if (myChoice.equals("Normal-Vy")) {
                //Koden här inne ska bara visa det vanliga med player-ids

            }
        }
    });


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
    layout.getChildren().addAll(submitButton,player_Id1TF,player_id2TF,game_IdTF,dateTF);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    popupWindow.setScene(scene);
    popupWindow.show();


    /*Label popupLabel = new Label();
    popupLabel.setText("Är du säker?");
    Button nejButton = new Button("NEJ");
    nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

    Button jaButton = new Button("JA");
    jaButton.setOnAction(e -> { popupWindow.close();});*/
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
        popupWindow.setTitle("Ändringsformulär");
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
        submitButton.setOnAction(this::register3);


        /*Label popupLabel = new Label();
        popupLabel.setText("Är du säker?");
        Button nejButton = new Button("NEJ");
        nejButton.setOnAction(e -> popupWindow.close()); //Stänger ned popup-rutan

        Button jaButton = new Button("JA");
        jaButton.setOnAction(e -> { popupWindow.close();});*/

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

        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();

        Player playerForPVPMatch = returnPlayer2(Integer.parseInt(player_Id1TF.getText()),em);

        Player playerForPVPMatch2 = returnPlayer2(Integer.parseInt(player_id2TF.getText()),em);


        Game gameForPVP = returnGame2(Integer.parseInt(game_IdTF.getText()),em);


        viewController.addPvPMatch(playerForPVPMatch,playerForPVPMatch2,gameForPVP,dateTF.getText());

        //viewController.addPvPMatch(Integer.parseInt(match_IdTF.getText()), Integer.parseInt(player_Id1TF.getText()),Integer.parseInt(player_id2TF.getText()),Integer.parseInt(game_IdTF.getText()),dateTF.getText());
        em.close();
        popupWindow.close();
    }

//OBS register3 är precis som register2 fast här är det som var (int) player_Id1 propertyn för PvP klassen
    //istället det nuvarande (Player) player_1, metoden returnPlayer
    public void register3(ActionEvent e){
        PvP PvPMatchSelected = mainTable.getSelectionModel().getSelectedItem();

        playerController = new PlayerController();
        Player playerForPVPMatch = playerController.returnPlayer(Integer.parseInt(player_Id1TF.getText()));
        Player playerForPVPMatch2 = playerController.returnPlayer(Integer.parseInt(player_id2TF.getText()));

        gameController = new GameController();
        Game gameForPVP = gameController.getGame(Integer.parseInt(game_IdTF.getText()));

        PvPMatchSelected.updateMatch(playerForPVPMatch,
                playerForPVPMatch2,gameForPVP,Integer.parseInt(winner_IdTF.getText()),
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
