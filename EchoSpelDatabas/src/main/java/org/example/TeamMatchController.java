package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;
import static org.example.Main.ENTITY_MANAGER_FACTORY;


public class TeamMatchController {
    private ObservableList<TeamMatch> teamMatchObservableList;

    public TeamMatchController() {
        teamMatchObservableList = FXCollections.observableArrayList();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM TeamMatch c WHERE c.matchId IS NOT NULL";
        TypedQuery<TeamMatch> tq = em.createQuery(strQuery, TeamMatch.class);

        try {
            List<TeamMatch> teamMatches = tq.getResultList();
            teamMatchObservableList.addAll(teamMatches);
            System.out.println(teamMatchObservableList.toString());
        } catch (NoResultException e){
            e.printStackTrace();

        }finally {
            em.close();
        }
    }

    public void addTeamMatch(Team teamId1, Team teamId2, Game gameId, String date) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        TeamMatch addMatch = new TeamMatch();

        addMatch.setTeamId1(teamId1);
        addMatch.setTeamId2(teamId2);
        addMatch.setGameId(gameId);
        addMatch.setDate(date);

        try {
            et = em.getTransaction();
            et.begin();

            em.persist(addMatch);
            et.commit();
        } catch (Exception e) {
            if(et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
         finally {
            em.close();
            teamMatchObservableList.add(addMatch);
        }
    }


    public void removeTeamMatch(TeamMatch teamMatch){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        teamMatchObservableList.remove(teamMatch);
        TeamMatch deleteMatch;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            deleteMatch = entityManager.find(TeamMatch.class, teamMatch.getMatchId());

            entityManager.remove(deleteMatch);
            entityManager.flush();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            entityManager.close();

        }
    }

    public void resolveMatch(TeamMatch selectedMatch) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        teamMatchObservableList.remove(selectedMatch);
        TeamMatch matchToBeChanged;
        try {
            et = em.getTransaction();
            et.begin();

            matchToBeChanged = em.find(TeamMatch.class, selectedMatch.getMatchId());
            matchToBeChanged.changeTeamMatch(selectedMatch.getWinnerName(),selectedMatch.getScoreT1(),selectedMatch.getScoreT2());

            em.merge(matchToBeChanged);
            et.commit();
            teamMatchObservableList.add(matchToBeChanged);
        } catch (Exception e){
           if(et != null){
               et.rollback();
           }
            e.printStackTrace();
        } finally {
            em.close();
        }
        em.close();
    }

  /*  public TeamMatch getTeamMatch(int matchId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        TeamMatch teamMatch = new TeamMatch();
        try {
            et = em.getTransaction();
            et.begin();
            teamMatch = em.find(TeamMatch.class, matchId);
        }catch (NoResultException e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return teamMatch;
    } */

    public ObservableList<TeamMatch> getObservableTeamMatch(){
        return teamMatchObservableList;
    }
}

