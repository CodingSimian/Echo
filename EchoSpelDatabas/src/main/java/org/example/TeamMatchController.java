package org.example;
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;




public class TeamMatchController {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");


    public static void addTeamMatch(int teamId1, int teamId2, int gameId, int winnerId,String date , int scoreT1, int scoreT2) {
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

/*    public void updateTeamMatch(int matchId,int teamId1, int teamId2, int gameId, int winnerId,String date , int scoreT1, int scoreT2){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        //Behöver mer kärlek


        try{
            et = em.getTransaction();
            et.begin();
            TeamMatch teamMatch;
            teamMatch= em.find(TeamMatch.class, matchId);
            teamMatch.updateTeamMatch(matchId,teamId1,teamId2,gameId,winnerId,date,scoreT1,scoreT2);
            em.merge(teamMatch);
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

    } */

    public List<TeamMatch> getAllMatches(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        List<TeamMatch> teamMatchList = null;
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
        }
        return teamMatchList;

    }
}





