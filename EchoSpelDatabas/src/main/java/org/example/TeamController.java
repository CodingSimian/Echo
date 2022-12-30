package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class TeamController {

    private ObservableList<Team> teamObservableList = FXCollections.observableArrayList() ;


    public TeamController(){
       uppdateTeamObservabelList();

    }

    public  void addTeam(String name, Game game){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Team team = new Team();
        team.setGame(game);
        team.setName(name);

        try{
            et = em.getTransaction();
            et.begin();
            em.merge(team);

            et.commit();

        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        uppdateTeamObservabelList();
    }


    public void updateTeam(Team team){


        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        teamObservableList.remove(team);
        teamObservableList.add(team);
        try{
            et = em.getTransaction();
            et.begin();
            em.merge(team);
            et.commit();

        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        em.close();
        uppdateTeamObservabelList();

    }

    public  void removeTeam(Team team){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Team teamToRemove;
        List<Player> members = team.getTeamMembers();
        System.out.println(members.size());
        for(int i = 0; i< members.size(); i++){
            members.get(i).setTeam_IdNull();
            updatePlayers(members);
        }


        try{
            et = em.getTransaction();
            et.begin();
            teamToRemove = em.find(Team.class,team.getTeamId());
            teamToRemove.setGame(null);
            em.remove(teamToRemove);
            et.commit();

        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        uppdateTeamObservabelList();

    }

    public void uppdateTeamObservabelList(){
        teamObservableList.clear();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Team e WHERE e.teamId IS NOT NULL";
        TypedQuery<Team> tQ = em.createQuery(strQuery, Team.class);



        try{
            List<Team> temp = tQ.getResultList();
            teamObservableList.addAll(temp);

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {

        }

    }

    public List<Game>getAllGames(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Game e WHERE e.gameId IS NOT NULL";

        TypedQuery<Game> tQ = em.createQuery(strQuery, Game.class);
        List<Game> temp = new ArrayList<>();



        try{

            temp = tQ.getResultList();


        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {

        }

        return temp;


    }

    public List<Player>getAllPlayers(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Player e ";

        TypedQuery<Player> tQ = em.createQuery(strQuery, Player.class);
        List<Player> temp = new ArrayList<>();



        try{

            temp = tQ.getResultList();


        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {

        }

        return temp;

    }

    public void updatePlayers(List<Player> players){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        ListIterator<Player> Iterator = players.listIterator();
        System.out.println(players.size());


        try{
            et = em.getTransaction();
            et.begin();
            while(Iterator.hasNext()) {
                em.merge(Iterator.next());
            }
            et.commit();


        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        em.close();

    }


    public ObservableList<Team> getTeamObservableList(){
        return  teamObservableList;
    }



}
