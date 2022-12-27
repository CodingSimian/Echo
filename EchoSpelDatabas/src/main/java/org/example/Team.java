package org.example;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinColumn(name = "game_Id")
    private Game game;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_Id")
    private List<Player> teamMembers = new ArrayList<>();

    public List<Player> getTeamMembers(){
        return teamMembers;
    }
    public void removeTeamMember(Player player){
        teamMembers.remove(player);
        player.setTeam_IdNull();
    }
    public void addTeamMember(Player player){
        teamMembers.add(player);
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }




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


    @Override
    public String toString(){
        return this.getName();
    }
}
