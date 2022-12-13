package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name = "tvt")
public class TeamMatch implements Serializable {

    public TeamMatch() {
    }

        @Id
        @Column(name = "match_Id")
        private int matchId;

        @Column(name = "team_Id1")
        private int teamId1;

        @Column(name = "team_Id2")
        private int teamId2;

        @Column(name = "game_Id" )
        private int gameId;

        @Column(name = "winner_Id" )
        private int winnerId;

        @Column(name = "date" )
        private String date;

        @Column(name = "score_T1" )
        private int scoreT1;

        @Column(name = "score_T2" )
        private int scoreT2;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId1() {
        return teamId1;
    }

    public void setTeamId1(int teamId1) {
        this.teamId1 = teamId1;
    }

    public int getTeamId2() {
        return teamId2;
    }

    public void setTeamId2(int teamId2) {
        this.teamId2 = teamId2;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScoreT1() {
        return scoreT1;
    }

    public void setScoreT1(int scoreT1) {
        this.scoreT1 = scoreT1;
    }

    public int getScoreT2() {
        return scoreT2;
    }

    public void setScoreT2(int scoreT2) {
        this.scoreT2 = scoreT2;
    }
}


