package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class TeamController {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");

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
    }

    public void updateTeam(int teamDid, String name, int gameId ){

        Team team = new Team();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try{
            et = em.getTransaction();
            et.begin();
            team = em.find(Team.class,teamDid);
            team.setName(name);
            team.setGameId(gameId);
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

    public  void removeTeam(int teamId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Team team = new Team();

        try{
            et = em.getTransaction();
            et.begin();
            team = em.find(Team.class,teamId);
            em.remove(team);
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

    public void getTeamMembers(int teamId){
     EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        //TODO när Spelare klassen är kopplad gör klart metod för att hämta alla spelare med motsvarande teamId
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



}
