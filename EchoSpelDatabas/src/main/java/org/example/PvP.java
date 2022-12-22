package org.example;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
@Table(name="PvP") //name of the table
public class PvP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.AUTO kommer att leta efter en tabell som literally heter
    //echo.hibernate_sequence, utan denna tabell så kan man inte använda sig av den GenerationType'n
    @Column(name="match_Id")
    private int match_Id;

    @ManyToOne
    //@Column(name = "player_Id1")
    @JoinColumn(name = "player_Id1") //här stod det player_Id förut, samma på player_Id2
    private Player player_1;

    @ManyToOne
    //@Column(name = "player_Id2")
    @JoinColumn(name = "player_Id2", insertable = true, updatable = true)
    private Player player_2;


    @ManyToOne
    //@Column(name = "game_Id")
    @JoinColumn(name = "game_Id")
    private Game game_Id;

    @Column(name = "winner_Id")
    private int winner_Id;

    @Column(name = "date")
    private String date;

    @Column(name = "score_P1")
    private int score_p1;

    @Column(name = "score_P2")
    private int score_P2; //player_Id1-2 och game_Id är foreign key, använd
    //ManyToOne elr JoinColumn, player_id referar till player tabellen, och game_Id referar till game tabellen


    public int getMatch_Id() {
        return match_Id;
    }

    public void setMatch_Id(int match_Id) {
        this.match_Id = match_Id;
    }

    public Player getPlayer_Id1() {
        return player_1;
    }

    public void setPlayer_1(Player player_1) {
        this.player_1 = player_1;
    }

    public Player getPlayer_Id2() {
        return player_2;
    }

    public void setPlayer_2(Player player_2) {
        this.player_2 = player_2;
    }

    public Game getGame_Id() {
        return game_Id;
    }

    public void setGame_Id(Game game_Id) {
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

    public int getScore_P1() {
        return score_p1;
    }

    public void setScore_p1(int score_p1) {
        this.score_p1 = score_p1;
    }

    public int getScore_P2() {
        return score_P2;
    }

    public void setScore_P2(int score_p2) {
        this.score_P2 = score_p2;
    }

    public void updateMatch( Player player_id1, Player player_id2, Game game_Id, int winner_Id, String date, int score_p1, int score_P2) {
        this.player_1 = player_id1;
        this.player_2 = player_id2;
        this.game_Id = game_Id;
        this.winner_Id = winner_Id;
        this.date = date;
        this.score_p1 = score_p1;
        this.score_P2 = score_P2;
    }
}
