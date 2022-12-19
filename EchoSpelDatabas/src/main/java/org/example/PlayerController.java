package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class PlayerController {


    public  void addPlayer(int Player_Id, String fName, String lName, String nickName, int postal_Numbr, String postal_city,
                           String country, String email, int team_Id  ){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try{
            et = em.getTransaction();
            et.begin();
            Player player = new Player();
            player.setPlayer_Id(player.getPlayer_Id());
            player.setfName(fName);
            player.setlName(lName);
            player.setNickName(nickName);
            player.setPostal_Numbr(postal_Numbr);
            player.setPostal_city(postal_city);
            player.setCountry(country);
            player.setE_mail(email);
            player.setTeam_Id(team_Id);
            em.persist(player);
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

    public void updatePlayer(int player_Id, int team_Id ){
        Player player = new Player();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try{
            et = em.getTransaction();
            et.begin();
            player = em.find(Player.class, player_Id);
            player.setPlayer_Id(player_Id);
            player.setTeam_Id(team_Id);
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

    public  void removePlayer(int player_Id, int team_Id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Player player = new Player();

        try{
            et = em.getTransaction();
            et.begin();
            player = em.find(Player.class,player_Id);
            em.remove(player);
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
