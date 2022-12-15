package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

public class MiscViews { //Denna java klass ska användas för att kunna skapa upp menyn.
private BorderPane root2Scene;
private VBox optionsVBox;
private Button spelareButton;
private Button lagButton;
private Button matchButton;
private Button turneringButton;
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
        spelareButton.setPrefWidth(100);
        //ALlt detta kan vi mycket enklare och snyggare ändra med css senare i projektet, men tills vidare duger detta

        lagButton = new Button("Lag");
        //lagButton.setOnAction(ActionEvent -> scene.setRoot());
        lagButton.setPrefWidth(100);

        matchButton = new Button("Match");
        matchButton.setPrefWidth(100);

        turneringButton = new Button("Turnering");
        //turneringButton.setOnAction(ActionEvent -> scene.setRoot());
        turneringButton.setPrefWidth(100);

        alternativ = new Label("Välj vad du vill konfigurera");
        alternativ.setFont(new Font("System",18));

        optionsVBox.getChildren().addAll(alternativ,spelareButton,lagButton,matchButton,turneringButton);
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

    public Button getMatchButton() {
        return matchButton;
    }

    public void setMatchButton(Button matchButton) {
        this.matchButton = matchButton;
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
}
