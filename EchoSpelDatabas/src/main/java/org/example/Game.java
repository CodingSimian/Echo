package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

    @Entity
    @Table(name = "game")
    public class Game{

        @Id
        @Column(name = "game_Id" )
        private int gameId;

        @Column(name = "name")
        private String gameName;

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

       // @Column(name = "Points type")
        //private int pointType;

        public Game() {
        }

        public Game (String gameName){
            this.gameName = gameName;
        }

        public void updateGame (String gameName){
            this.gameName = gameName;
        }

        @Override
        public String toString(){
            return this.gameName;
        }


    }

