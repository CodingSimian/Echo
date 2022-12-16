package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

    // Properties
    @Id
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "game_id" )
    private int gameId;


    // Getters n Setters

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

    @Override
    public String toString(){
        return this.getName();
    }
}
