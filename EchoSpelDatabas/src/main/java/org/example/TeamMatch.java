package org.example;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Entity;


@Entity
@Table(name="tvt")
public class TeamMatch implements Serializable {

    public TeamMatch() {
    }

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        @Column(name = "match_Id")
        private int matchId;

        @ManyToOne
        @JoinColumn (name = "team_Id1")
        private Team teamId1;

        @ManyToOne
        @JoinColumn (name = "team_Id2")
        private Team teamId2;

        @ManyToOne
        @JoinColumn (name = "game_Id")
        private Game gameId;

        @Column(name = "winner_Id" )
        private String winnerName;

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

    public Team getTeamId1() {
        return teamId1;
    }

    public void setTeamId1(Team teamId1) {
        this.teamId1 = teamId1;
    }

    public Team getTeamId2() {
        return teamId2;
    }

    public void setTeamId2(Team teamId2) {
        this.teamId2 = teamId2;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public String getWinnerName() {
        return this.winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
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

    public void changeTeamMatch (String winnerName, int scoreT1, int scoreT2){
        this.winnerName = winnerName;
        this.scoreT1 = scoreT1;
        this.scoreT2 = scoreT2;

    }
}


