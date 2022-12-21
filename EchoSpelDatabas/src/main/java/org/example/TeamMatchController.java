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
        TeamMatch addMatch = new TeamMatch();

        addMatch.setTeamId1(teamId1);
        addMatch.setTeamId2(teamId2);
        addMatch.setGameId(gameId);
        addMatch.setWinnerId(winnerId);
        addMatch.setDate(date);
        addMatch.setScoreT1(scoreT1);
        addMatch.setScoreT2(scoreT2);

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

   /* public void removeTeamMatch(int matchId){
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
    } */


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

  /*  public void changeMatch(int matchId, int winnerId, int scoreT1, int scoreT2){
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
    } */

    public void changeMatch2 (TeamMatch changeMatch) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        teamMatchObservableList.remove(changeMatch);
        TeamMatch changeThisMatch;
        try {
            et = em.getTransaction();
            et.begin();

            changeThisMatch = em.find(TeamMatch.class, changeMatch.getMatchId());
            changeThisMatch.changeTeamMatch(changeMatch.getMatchId(),changeMatch.getTeamId1(),changeMatch.getTeamId2(),changeMatch.getGameId(),changeMatch.getWinnerId(),changeMatch.getDate(),changeMatch.getScoreT1(),changeMatch.getScoreT2());
            em.merge(changeThisMatch);
            et.commit();
            teamMatchObservableList.add(changeThisMatch);
        } catch (Exception e){
            if (et != null){
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
            em.close();
    }

 /*   public List<TeamMatch> getAllTeamMatches(){
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

    } */

    public ObservableList<TeamMatch> getObservableTeamMatch(){
        return teamMatchObservableList;
    }
}





