package org.example;


import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Niclas Larsson
public class Graphics  {



    // Properties
    // Kommer ha en av varje typ av view för att kuna växla view,
    private Scene scene;
    private Stage Mainstage;
    private PersonalView personalView;







    Graphics(){
    personalView = new PersonalView();

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
        scene = new Scene(personalView.getPane(),800,700);
        Mainstage = new Stage();
        Mainstage.setScene(scene);

        return Mainstage;
    }
}





