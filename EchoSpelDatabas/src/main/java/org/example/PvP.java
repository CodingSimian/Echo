package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="PvP") //name of the table
public class PvP implements Serializable {

    @Id
    @Column(name="match_Id", unique=true)
    private int match_Id;

    @Column(name = "player_Id1")
    private int player_id1;

    @Column(name = "player_Id2")
    private int player_id2;

    @Column(name = "game_Id")
    private int game_Id;

    @Column(name = "winner_Id")
    private int winner_Id;

    @Column(name = "date") //Kanske behöver byta datatyp till date, om det finnns
    //I java.
    private String date;

    @Column(name = "score_p1")
    private int score_p1;

    @Column(name = "score_p2")
    private int score_p2; //player_Id1-2 och game_Id är foreign key, använd
    //ManyToOne elr JoinColumn, player_id referar till player tabellen, och game_Id referar till game tabellen


    public int getMatch_Id() {
        return match_Id;
    }

    public void setMatch_Id(int match_Id) {
        this.match_Id = match_Id;
    }

    public int getPlayer_id1() {
        return player_id1;
    }

    public void setPlayer_id1(int player_id1) {
        this.player_id1 = player_id1;
    }

    public int getPlayer_id2() {
        return player_id2;
    }

    public void setPlayer_id2(int player_id2) {
        this.player_id2 = player_id2;
    }

    public int getGame_Id() {
        return game_Id;
    }

    public void setGame_Id(int game_Id) {
        this.game_Id = game_Id;
    }

    public int getWinner_Id() {
        return winner_Id;
    }

    public void setWinner_Id(int winner_Id) {
        this.winner_Id = winner_Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore_p1() {
        return score_p1;
    }

    public void setScore_p1(int score_p1) {
        this.score_p1 = score_p1;
    }

    public int getScore_p2() {
        return score_p2;
    }

    public void setScore_p2(int score_p2) {
        this.score_p2 = score_p2;
    }
}
