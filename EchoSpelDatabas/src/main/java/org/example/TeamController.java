package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class TeamController {

    private ObservableList<Team> teamObservableList = FXCollections.observableArrayList() ;


    public TeamController(){
       uppdateTeamObservabelList();

    }

    public  void addTeam(int id, String name){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try{
            et = em.getTransaction();
            et.begin();
            Team team = new Team();
            team.setName(name);
            team.setGameId(id);
            em.persist(team);
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

    }

    public  void removeTeam(Team team){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Team teamToRemove;
        List<Player> members = getTeamMembers(team.getTeamId());
        System.out.println(members.size());
        for(int i = 0; i< members.size(); i++){
            members.get(i).setTeam_IdNull();
            updatePlayer(members.get(i));
        }


        try{
            et = em.getTransaction();
            et.begin();
            teamToRemove = em.find(Team.class,team.getTeamId());
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



    public Team getTeam(int teamId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Team team = new Team();

        try{
            et = em.getTransaction();
            et.begin();
            team = em.find(Team.class,teamId);


        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return team;

    }

    public List<Player>getOtherPlayers(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Player e WHERE NOT e.team_Id =" + id ;
        String strQuery2 = "SELECT e From Player e WHERE e.team_Id IS NULL";
        Query q = em.createNativeQuery("SELECT * FROM player e WHERE e.team_Id IS NULL");
        TypedQuery<Player> tQ = em.createQuery(strQuery, Player.class);
        TypedQuery<Player> tq2 = em.createQuery(strQuery2, Player.class);
        List<Player> temp = new ArrayList<>();



        try{
             temp = tQ.getResultList();
             temp.addAll(tq2.getResultList());


        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {

        }

        return temp;

    }

    public List<Player>getTeamMembers(int id){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Player e WHERE  e.team_Id =" + id;
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

    public void updatePlayer(Player player){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;


        try{
            et = em.getTransaction();
            et.begin();
            em.merge(player);
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
