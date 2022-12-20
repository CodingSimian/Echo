package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;
import java.util.List;
import static org.example.Main.ENTITY_MANAGER_FACTORY;


public class TeamMatchController {
    private ObservableList<TeamMatch> teamMatchObservableList; //Observable list används eftersom den ändrar sin grafiska aspekt så fort dess värden förändras

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

    public void addTeamMatch(int teamId1, int teamId2, int gameId, int winnerId, String date , int scoreT1, int scoreT2) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            TeamMatch match = new TeamMatch();
            match.setTeamId1(teamId1);
            match.setTeamId2(teamId2);
            match.setGameId(gameId);
            match.setWinnerId(winnerId);
            match.setDate(date);
            match.setScoreT1(scoreT1);
            match.setScoreT2(scoreT2);
            em.persist(match);
            et.commit();
        } catch (Exception e) {
            if(et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
         finally {
            em.close();
        }
    }


    public TeamMatch getTeamMatch(int matchId) {
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
    }

    public void removeTeamMatch(int matchId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        TeamMatch teamMatch;

        try{
            et = em.getTransaction();
            et.begin();
            teamMatch = em.find(TeamMatch.class,matchId);
            em.remove(teamMatch);
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
    }


    public void removeTeamMatch2(TeamMatch teamMatch){
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

    public void changeMatch(int matchId, int winnerId, int scoreT1, int scoreT2){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        TeamMatch teamMatch;
        try{
            et = em.getTransaction();
            et.begin();
            teamMatch = em.find(TeamMatch.class, matchId);
            teamMatch.setWinnerId(winnerId);
            teamMatch.setScoreT1(scoreT1);
            teamMatch.setScoreT2(scoreT2);

            em.persist(teamMatch);
            et.commit();
        }
        catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public List<TeamMatch> getAllTeamMatches(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        List<TeamMatch> teamMatchList = null;
        boolean isSuccess = true;
        //String strQuery = "SELECT t FROM TeamMatch t WHERE t.matchId IS NOT NULL";
       // TypedQuery<TeamMatch> tm = em.createQuery(strQuery, TeamMatch.class);
       // List<TeamMatch> teamMatchList = new ArrayList<>();

        try{
            et = em.getTransaction();
            et.begin();
            TypedQuery<TeamMatch> getAllMatches = em.createQuery("from TeamMatch", TeamMatch.class);
            teamMatchList = getAllMatches.getResultList();
            et.commit();
            //teamMatchList = tm.getResultList();

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
            System.out.println(isSuccess);
        }
        return teamMatchList;

    }

    public ObservableList<TeamMatch> getObservableTeamMatch(){
        return teamMatchObservableList;
    }
}





