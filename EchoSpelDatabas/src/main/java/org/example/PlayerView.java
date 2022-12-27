package org.example;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.persistence.Table;


public class PlayerView extends Application {

    TableView<Player> table;
    TextField player_IdInput, fNameInput, lNameInput, nickNameInput, adressInput, postal_NumbrInput,
            postal_cityInput, countryInput, e_mailInput, team_IdInput;

    @Override
    public void start(Stage stage) throws Exception {


        TableColumn tableColumn = new TableColumn();


        //Columns names
        TableColumn<Player, String> playIdColumn = new TableColumn<>("Player_Id");
        playIdColumn.setMinWidth(200);
        playIdColumn.setCellValueFactory(new PropertyValueFactory<>("Player_Id"));

        TableColumn<Player, String> fnameColumn = new TableColumn<>("fName");
        fnameColumn.setMinWidth(200);
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fName"));

        TableColumn<Player, String> lnameColumn = new TableColumn<>("lName");
        lnameColumn.setMinWidth(200);
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lName"));

        TableColumn<Player, String> nickNameColumn = new TableColumn<>("NickName");
        nickNameColumn.setMinWidth(200);
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("NickName"));

        TableColumn<Player, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

        TableColumn<Player, String> postNumColumn = new TableColumn<>("Postal number");
        postNumColumn.setMinWidth(200);
        postNumColumn.setCellValueFactory(new PropertyValueFactory<>("Postal number"));

        TableColumn<Player, String> postCitColumn = new TableColumn<>("Postal city");
        postCitColumn.setMinWidth(200);
        postCitColumn.setCellValueFactory(new PropertyValueFactory<>("Postal city"));

        TableColumn<Player, String> counColumn = new TableColumn<>("Country");
        counColumn.setMinWidth(200);
        counColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<Player, String> e_mailColumn = new TableColumn<>("E_mail");
        e_mailColumn.setMinWidth(200);
        e_mailColumn.setCellValueFactory(new PropertyValueFactory<>("E_mail"));

        TableColumn<Player, String> team_idColumn = new TableColumn<>("Team_Id");
        team_idColumn.setMinWidth(200);
        team_idColumn.setCellValueFactory(new PropertyValueFactory<>("Team_Id"));

        //Input
        player_IdInput = new TextField();
        player_IdInput.setPromptText("Player_Id");
        player_IdInput.setMinWidth(100);

        fNameInput = new TextField();
        fNameInput.setPromptText("fName");
        fNameInput.setMinWidth(100);

        lNameInput = new TextField();
        lNameInput.setPromptText("lName");
        lNameInput.setMinWidth(100);

        nickNameInput = new TextField();
        nickNameInput.setPromptText("Nickname");
        nickNameInput.setMinWidth(100);

        adressInput = new TextField();
        adressInput.setPromptText("Address");
        adressInput.setMinWidth(100);

        postal_NumbrInput = new TextField();
        postal_NumbrInput.setPromptText("Postal_numbr");
        postal_NumbrInput.setMinWidth(100);

        postal_cityInput = new TextField();
        postal_cityInput.setPromptText("Postal_city");
        postal_cityInput.setMinWidth(100);

        countryInput = new TextField();
        countryInput.setPromptText("Country");
        countryInput.setMinWidth(100);

        e_mailInput = new TextField();
        e_mailInput.setPromptText("E_mail");
        e_mailInput.setMinWidth(100);

        team_IdInput = new TextField();
        team_IdInput.setPromptText("Team_Id");
        team_IdInput.setMinWidth(100);

        //Button and building UI
        /*Button getButton = new Button("Get");
        getButton.setOnAction(event -> getButtonClicked());*/
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addButtonClicked());
        Button updButton = new Button("Update");
        updButton.setOnAction(event -> editButtonClicked());
        Button deletButton = new Button("Remove");
        deletButton.setOnAction(event -> deletButtonClicked());


        Button button = new Button();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(player_IdInput, fNameInput, lNameInput, nickNameInput, adressInput, postal_NumbrInput,
                postal_cityInput, countryInput, e_mailInput, team_IdInput, addButton, updButton, deletButton); /*getButton*/

        //Get all the columns names into the table and show up the table
        table = new TableView<>();
        table.setItems(getPlayer());
        table.getColumns().addAll(playIdColumn, fnameColumn, lnameColumn, nickNameColumn, addressColumn, postNumColumn,
                postCitColumn, counColumn, e_mailColumn, team_idColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
    }

        //Add button clicked
        public void addButtonClicked () {
            Player player = new Player();
            player.setPlayer_Id(player.getPlayer_Id());
            player.setfName(player.getfName());
            player.setlName(player.getlName());
            player.setNickName(player.getNickName());
            player.setAdress(player.getAdress());
            player.setPostal_Numbr(player.getPostal_Numbr());
            player.setPostal_city(player.getPostal_city());
            player.setCountry(player.getCountry());
            player.setE_mail(player.getE_mail());
            player.setTeam_Id(player.getTeam_Id());
            table.getItems().add(player);
        }

        public void deletButtonClicked(){
        ObservableList<Player>playerSelected, allPlayers;
        allPlayers = table.getItems();
        playerSelected = table.getSelectionModel().getSelectedItems();
        playerSelected.forEach(allPlayers::remove);
        }

        public void editButtonClicked(){
        Player player = new Player();
        ObservableList<Player>playerSelected, allPlayers;
        allPlayers = table.getItems();
        playerSelected = table.getSelectionModel().getSelectedItems();
            player.setPlayer_Id(player.getPlayer_Id());
            player.setfName(player.getfName());
            player.setlName(player.getlName());
            player.setNickName(player.getNickName());
            player.setAdress(player.getAdress());
            player.setPostal_Numbr(player.getPostal_Numbr());
            player.setPostal_city(player.getPostal_city());
            player.setCountry(player.getCountry());
            player.setE_mail(player.getE_mail());
            player.setTeam_Id(player.getTeam_Id());
            playerSelected.forEach(allPlayers::setAll);

        }



        //Get all players
        public ObservableList<Player> getPlayer () {

            Player player = new Player();
            ObservableList<Player> players = FXCollections.observableArrayList();
            players.add(new Player(player.getPlayer_Id()));
            players.add(new Player(player.getfName()));
            players.add(new Player(player.getlName()));
            players.add(new Player(player.getNickName()));
            players.add(new Player(player.getAdress()));
            players.add(new Player(player.getPostal_Numbr()));
            players.add(new Player(player.getPostal_city()));
            players.add(new Player(player.getCountry()));
            players.add(new Player(player.getE_mail()));
            players.add(new Player(player.getTeam_Id()));
            return players;
        }






































    /*private TableView <Player> table;
    private TableColumn<Player, String> Player_Id;
    private TableColumn <Player, String> fName;
    private TableColumn <Player, String> lName;
    private TableColumn <Player, String> nickName;
    private TableColumn <Player, String> adress;
    private TableColumn <Player, Integer> Postal_Numbr;
    private TableColumn <Player, String> Postal_city;
    private TableColumn <Player, String> country;
    private TableColumn <Player, String> e_mail;
    private TableColumn <Player, Integer> team_Id;*/

    /*public void initialize (){

        Player_Id.setCellValueFactory(new PropertyValueFactory<Player, Integer >("Player_Id"));
        fName.setCellValueFactory(new PropertyValueFactory<Player, String>("fName"));
        lName.setCellValueFactory(new PropertyValueFactory<Player, String>("lName"));
        nickName.setCellValueFactory(new PropertyValueFactory<Player, String>("nickName"));
        adress.setCellValueFactory(new PropertyValueFactory<Player, String>("adress"));
        Postal_Numbr.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Postal_Number"));
        Postal_city.setCellValueFactory(new PropertyValueFactory<Player, String>("Postal_city"));
        country.setCellValueFactory(new PropertyValueFactory<Player, String>("country"));
        e_mail.setCellValueFactory(new PropertyValueFactory<Player, String>("e_mail"));
        team_Id.setCellValueFactory(new PropertyValueFactory<Player, Integer>("team_Id"));
    }*/



}
