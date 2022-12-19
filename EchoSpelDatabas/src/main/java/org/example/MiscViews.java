package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

public class MiscViews { //Denna java klass ska användas för att kunna skapa upp menyn.
private BorderPane root2Scene;
private VBox optionsVBox;
private Button spelareButton;
private Button lagButton, matchPvPButton,turneringButton,matchTvTButton;
private Label alternativ;

    //private EventHandler<ActionEvent> setOnAction(EventHandler<ActionEvent> actionEventEventHandler) {}
//private MiscController controller;


    public MiscViews() throws IOException {
        buildUI();
    }
    public void buildUI() throws IOException {
        root2Scene = new BorderPane();
        optionsVBox = new VBox();
        optionsVBox.setSpacing(30);


         spelareButton = new Button("Spelare");
        //spelareButton.setOnAction(ActionEvent -> scene.setRoot());
        spelareButton.setPrefWidth(150);
        //ALlt detta kan vi mycket enklare och snyggare ändra med css senare i projektet, men tills vidare duger detta

        lagButton = new Button("Lag");
        //lagButton.setOnAction(ActionEvent -> scene.setRoot());
        lagButton.setPrefWidth(150);

        matchTvTButton = new Button("Match (Team vs Team)");
        matchTvTButton.setPrefWidth(150);


        matchPvPButton = new Button("Match (Player vs Player)");
        matchPvPButton.setPrefWidth(150);

        turneringButton = new Button("Turnering");
        //turneringButton.setOnAction(ActionEvent -> scene.setRoot());
        turneringButton.setPrefWidth(150);

        alternativ = new Label("Välj vad du vill konfigurera");
        alternativ.setFont(new Font("System",18));

        optionsVBox.getChildren().addAll(alternativ,spelareButton,lagButton, matchPvPButton,turneringButton,matchTvTButton);
        optionsVBox.setAlignment(Pos.CENTER);

        root2Scene.setCenter(optionsVBox);
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

    public Button getTurneringButton() {
        return turneringButton;
    }

    public void setTurneringButton(Button turneringButton) {
        this.turneringButton = turneringButton;
    }

    public Label getAlternativ() {
        return alternativ;
    }

    public void setAlternativ(Label alternativ) {
        this.alternativ = alternativ;
    }
    /*homeButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        BorderPane pane = new BorderPane();
        Label label1 = new Label("Test");
        pane.setCenter(label1);
        scene.setRoot(pane);
    }
});*/
}
