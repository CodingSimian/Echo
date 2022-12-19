package org.example;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

// Niclas Larsson
public class Graphics  {



    // Properties
    // Kommer ha en av varje typ av view för att kuna växla view,
    private Scene scene;
    private Stage Mainstage;
    private PersonalView personalView;
    private PvPView daPvPView;
    private MiscViews optionsMenu;






    Graphics() throws IOException {
    personalView = new PersonalView(); //Eftersom konstruktorn skapar upp root noden samt hela scenen,
    optionsMenu = new MiscViews();
    daPvPView = new PvPView();
    }

    // Kommer användas för att byta mellan de olika Views,
    public void startview(ActionEvent e){

    }
    public void PersonalView(ActionEvent e){

    }
    public void TeamView(ActionEvent e){

    }
    public void PvPView(ActionEvent e){

    }
    public void TvTview(ActionEvent e){

    }
    public void spelareView(ActionEvent e){

    }




    // StartMetod som retunera en Stage till Main som sedan kör launch,
    // personalView ligger här tillfälligt
    public Stage start(){
        scene = new Scene(daPvPView.getRootPvPScene(),900,900);
        daPvPView.getMainMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(optionsMenu.getRoot2Scene());
            }
        });;
        //scene = new Scene(personalView.getPane(),900,900);
        Mainstage = new Stage();
        Mainstage.setTitle("Pied Pipers Corporate Data Management");
        Mainstage.setScene(scene);

        return Mainstage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getMainstage() {
        return Mainstage;
    }

    public void setMainstage(Stage mainstage) {
        Mainstage = mainstage;
    }

    public PersonalView getPersonalView() {
        return personalView;
    }

    public void setPersonalView(PersonalView personalView) {
        this.personalView = personalView;
    }

    public PvPView getDaPvPView() {
        return daPvPView;
    }

    public void setDaPvPView(PvPView daPvPView) {
        this.daPvPView = daPvPView;
    }

    public MiscViews getOptionsMenu() {
        return optionsMenu;
    }

    public void setOptionsMenu(MiscViews optionsMenu) {
        this.optionsMenu = optionsMenu;
    }
}





