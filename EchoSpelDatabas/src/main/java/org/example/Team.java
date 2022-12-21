package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    // Properties
    @Id
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "game_id" )
    private int gameId;


    @Transient
    private String gameName;





    // Getters n Setters

    public Team(){

    }

    public int getTeamId() {
        return teamId;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String game){
        this.gameName = game;
    }
    public String getGameName(){
        return this.gameName;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
