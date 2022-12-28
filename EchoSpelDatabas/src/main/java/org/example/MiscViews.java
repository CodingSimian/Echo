package org.example;

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
//Denna klass ska starta upp Huvudmenyn samt inloggningssidan.

import java.io.IOException;

public class MiscViews {
    private BorderPane root2Scene;
    private VBox optionsVBox;
    private Button lagButton, matchPvPButton, gameButton, matchTvTButton, backButton, logInButton, logOutButton,spelareButton;
    private Label alternativ;

    private TextField userTF,userTFSecondScene;

    private Stage popupWindow;

    private HBox top;

    private PersonalController controller;


    private BorderPane startRoot;

    private TableView<Personal> table;

    private TableColumn<Personal, String> firstNameColum;
    private TableColumn<Personal, String> lastNameColum;
    private TableColumn<Personal, String> nickNameColum;


    public MiscViews(TextField daUserTF) throws IOException {
        this.userTF = daUserTF;
        buildUI2();
        buildUI();

    }


    public void buildUI2() {
        controller = new PersonalController();


        table = new TableView<Personal>();
        startRoot = new BorderPane();
        table.setPrefSize(200, 300);

        logInButton = new Button("Logga in");
        logInButton.setOnAction(this::logInPressed);

        logOutButton = new Button("Logga ut");
        logOutButton.setOnAction(this::logOutPressed);

        top = new HBox();
        top.setSpacing(15);
        top.getChildren().addAll(logInButton, logOutButton);

        firstNameColum = new TableColumn<Personal, String>("Firstname");
        firstNameColum.setCellValueFactory(new PropertyValueFactory<Personal, String>("firstName"));

        TableColumn<Personal, String> lastNameColum = new TableColumn<Personal, String>("Lastname");
        lastNameColum.setCellValueFactory(new PropertyValueFactory<Personal, String>("lastName"));

        TableColumn<Personal, String> nickNameColum = new TableColumn<Personal, String>("NickName");
        nickNameColum.setCellValueFactory(new PropertyValueFactory<Personal, String>("nickName"));

        table.getColumns().addAll(firstNameColum, lastNameColum, nickNameColum);
        table.setItems(controller.getPersonal());
        table.setFocusTraversable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        startRoot.setTop(top);

        startRoot.setCenter(table);

    }

    private void logOutPressed(ActionEvent actionEvent) {
        userTF.setText("");
    }

    public void logInPressed(ActionEvent actionEvent) {
        Personal pers = table.getSelectionModel().getSelectedItem();

        //String daName = pers.getNickName();
        try {
            if (userTF.getText().isEmpty()) {
                userTF.setText(pers.getNickName());

            } else if (pers.getNickName().equals(userTF.getText())) {
                userTF.setText(pers.getNickName());

            } else if (pers.getNickName().equals(userTF.getText()) == false) {
                popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Warning");
                popupWindow.setMinHeight(50);
                popupWindow.setMinWidth(450);
                TextField tf = new TextField();
                tf.setText("Du måste logga ut först för att kunna logga in som någon annan");
                tf.setEditable(false);
                VBox layout = new VBox();
                layout.getChildren().add(tf);
                Scene scene = new Scene(layout);
                popupWindow.setScene(scene);
                popupWindow.show();
            }
        } catch (NullPointerException e) {
            popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Warning");
            popupWindow.setMinHeight(50);
            popupWindow.setMinWidth(450);

            TextField tf = new TextField();
            tf.setText("Klicka på den användaren du vill logga in som\n sedan klicka på logga-in-knappen");
            tf.setEditable(false);
            tf.setMinSize(400, 100);
            VBox layout = new VBox();
            layout.getChildren().add(tf);
            Scene scene = new Scene(layout);
            popupWindow.setScene(scene);
            popupWindow.show();

        }
    }

    public void buildUI() throws IOException {
        root2Scene = new BorderPane();
        optionsVBox = new VBox();
        optionsVBox.setSpacing(30);


        spelareButton = new Button("Spelare");
        spelareButton.setPrefWidth(150);

        lagButton = new Button("Lag");
        //lagButton.setOnAction(ActionEvent -> scene.setRoot());
        lagButton.setPrefWidth(150);

        matchTvTButton = new Button("Match (Team vs Team)");
        matchTvTButton.setPrefWidth(150);

        backButton = new Button("Tillbaka till inloggning-sidan");
        backButton.setPrefWidth(150);

        matchPvPButton = new Button("Match (Player vs Player)");
        matchPvPButton.setPrefWidth(150);

        gameButton = new Button("Game");
        //turneringButton.setOnAction(ActionEvent -> scene.setRoot());
        gameButton.setPrefWidth(150);

        alternativ = new Label("Välj vad du vill konfigurera");
        alternativ.setFont(new Font("System", 18));
        userTFSecondScene = userTF;
        //userTFSecondScene.setEditable(false);

        optionsVBox.getChildren().addAll(alternativ, spelareButton, lagButton, matchPvPButton, gameButton, matchTvTButton, backButton);
        optionsVBox.setAlignment(Pos.CENTER);

        root2Scene.setCenter(optionsVBox);
        root2Scene.setTop(userTFSecondScene);
    }


    public BorderPane getRoot2Scene() {
        return root2Scene;
    }

    public void setRoot2Scene(BorderPane root2Scene) {
        this.root2Scene = root2Scene;
    }

    public VBox getOptionsVBox() {
        return optionsVBox;
    }

    public void setOptionsVBox(VBox optionsVBox) {
        this.optionsVBox = optionsVBox;
    }

    public Button getSpelareButton() {
        return spelareButton;
    }

    public void setSpelareButton(Button spelareButton) {
        this.spelareButton = spelareButton;
    }

    public Button getLagButton() {
        return lagButton;
    }

    public void setLagButton(Button lagButton) {
        this.lagButton = lagButton;
    }

    public Button getMatchPvPButton() {
        return matchPvPButton;
    }

    public void setMatchPvPButton(Button matchPvPButton) {
        this.matchPvPButton = matchPvPButton;
    }

    public Button getGameButton() {
        return gameButton;
    }

    public void setGameButton(Button gameButton) {
        this.gameButton = gameButton;
    }

    public Button getMatchTvTButton() {
        return matchTvTButton;
    }

    public void setMatchTvTButton(Button matchTvTButton) {
        this.matchTvTButton = matchTvTButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public BorderPane getStartRoot() {
        return startRoot;
    }

    public Button getLogInButton() {
        return logInButton;
    }

    public TextField getUserTF() {
        return userTF;
    }

    public void setUserTF(TextField userTF) {
        this.userTF = userTF;
    }

    public TextField getUserTFSecondScene() {
        return userTFSecondScene;
    }

    public void setUserTFSecondScene(TextField userTFSecondScene) {
        this.userTFSecondScene = userTFSecondScene;
    }

    public HBox getTop() {
        return top;
    }




}
