package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class TeamController {


    public static void addPersonal(int id, String name){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try{
            et = em.getTransaction();
            et.begin();
            Team team = new Team();
            team.setName(name);
            team.setGameId(id);
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
}
