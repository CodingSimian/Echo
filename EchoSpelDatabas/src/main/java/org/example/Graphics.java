package org.example;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TeamMatchView teamMatchView;
    private TeamView myTeamView;

    private PlayerView playerView;

    private TextField userTF;
    private GameView myGameView;





    Graphics() throws IOException {
        Button homeButton = new Button("HomeButton");

        userTF = new TextField();
    personalView = new PersonalView(); //Eftersom konstruktorn skapar upp root noden samt hela scenen,
    optionsMenu = new MiscViews(userTF);
    daPvPView = new PvPView();
    teamMatchView = new TeamMatchView();
    myTeamView = new TeamView();
    playerView = new PlayerView();
    myGameView = new GameView(homeButton);

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
    public void TeamMatchview(ActionEvent e){

    }
    public void spelareView(ActionEvent e){

    }




    // StartMetod som retunera en Stage till Main som sedan kör launch,
    public Stage start(){
        scene = new Scene(optionsMenu.getStartRoot(),900,900);
        userTF.setEditable(false);
        userTF.setFocusTraversable(false);

        optionsMenu.getTop().getChildren().add(userTF);

    //optionsMenu.getRoot2Scene().setBottom(userTF);
        teamMatchView.getMainMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(optionsMenu.getRoot2Scene());
            }
        });

        myTeamView.getHomebutton().setOnAction(e-> scene.setRoot(optionsMenu.getRoot2Scene()));

        optionsMenu.getPersonalButton().setOnAction(e -> scene.setRoot(personalView.getPane()));

        optionsMenu.getLagButton().setOnAction(e -> scene.setRoot(myTeamView.getTeamView()));

        optionsMenu.getMatchTvTButton().setOnAction(e -> scene.setRoot(teamMatchView.getRootTeamMatchScene()));

        optionsMenu.getBackButton().setOnAction(e -> scene.setRoot(optionsMenu.getStartRoot()));

        optionsMenu.getMatchPvPButton().setOnAction(e -> scene.setRoot(daPvPView.getRootPvPScene()));

        optionsMenu.getGameButton().setOnAction(e -> scene.setRoot(myGameView.getPane()));
        myGameView.getHomeButton().setOnAction(e -> scene.setRoot(optionsMenu.getRoot2Scene()));
        personalView.getHomeButton().setOnAction(e -> scene.setRoot(optionsMenu.getRoot2Scene()));
        //optionsMenu.getSpelareButton().setOnAction(e -> scene.setRoot(playerView.get));

        optionsMenu.getLogInButton().setOnAction(e->{
            optionsMenu.logInPressed(e);
            if(optionsMenu.getUserTF().getText().isEmpty() == false){
                optionsMenu.getUserTFSecondScene().setText(optionsMenu.getUserTF().getText());
                daPvPView.getUserTF().setText(optionsMenu.getUserTF().getText());
                scene.setRoot(optionsMenu.getRoot2Scene());

            }

        });
        daPvPView.getMainMenuButton().setOnAction(e -> scene.setRoot(optionsMenu.getRoot2Scene()));


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

    /*public TeamMatchView getTeamMatchView() {
        return teamMatchView;
    }

    public void setTeamMatchView(TeamMatchView teamMatchView) {
        this.teamMatchView = teamMatchView;
    }*/
}






