package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OscarTestMain {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    public static void main(String[] args) {

        addTvTMatch(1,2,3,4,2,2,0);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addTvTMatch (int matchId,int teamId1, int teamId2, int gameId, int winnerId, int scoreT1, int scoreT2) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            TeamVsTeam match = new TeamVsTeam();
            match.setMatchId(matchId);
            match.setTeamId1(teamId1);
            match.setScoreT2(teamId2);
            match.setGameId(gameId);
            match.setWinnerId(winnerId);
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
 /*   public static void getTvTMatch (int matchId, int teamId1, int teamId2, int gameId, int winnerId, int scoreT1, int scoreT2) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT match FROM TVT WHERE "
    }
  */

}