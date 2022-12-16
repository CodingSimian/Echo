package org.example;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private MiscViews miscView;
    private TeamView teamView;


    private Button homeButton;









    Graphics() throws IOException {
        homeButton = new Button("Home");
        homeButton.setPrefSize(50,50);
        personalView = new PersonalView(homeButton);
        teamView = new TeamView(homeButton);
        miscView = new MiscViews();


        personalView.setHomeButton(homeButton);
        homeButton.setOnAction(event -> scene.setRoot(miscView.getRoot2Scene()));


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
        scene = new Scene(teamView.getTeamView(),800,700);
        Mainstage = new Stage();
        Mainstage.setScene(scene);

        return Mainstage;
    }
}





