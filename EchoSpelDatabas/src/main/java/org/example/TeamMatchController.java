package org.example;

import javax.persistence.*;


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


    public TeamMatch getMatch (int matchId) {
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
}





