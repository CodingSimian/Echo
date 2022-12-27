package org.example;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.query.NativeQuery;
import org.example.Personal;
import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class GameController {

    private ObservableList<Game> gameObservableList;

    public GameController (){
        gameObservableList = FXCollections.observableArrayList();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Game e WHERE e.id IS NOT NULL";
        TypedQuery<Game> g = em.createQuery(strQuery, Game.class);

        try{
            List<Game> temporary = g.getResultList();
            gameObservableList.addAll(temporary);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public void addGame(String gameName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Game game = new Game(gameName);
        try{
            et =em.getTransaction();
            et.begin();
            em.persist(game);
            et.commit();
        }catch(Exception ex){
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
            gameObservableList.add(game);
        }
    }

    public void updateGame(Game temporary){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        gameObservableList.remove(temporary);
        try{
            et = em.getTransaction();
            et.begin();
            em.merge(temporary);
            et.commit();
            gameObservableList.add(temporary);
        }catch(Exception ex){
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }

    public  void removeGame (Game gameId){

        //table get selection ?
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        gameObservableList.remove(gameId);
        Game game;
        String strQuery = "SELECT e FROM Game e WHERE e.gameName is NOT NULL";
    //   TypedQuery<Game> ps = em.createQuery(strQuery, Game.class);
    //   ps.setParameter("game", gameId.getGameId());
    //   TypedQuery<Game> ps2 = em.createQuery(strQuery,Game.class);


        try {
            et = em.getTransaction();
            et.begin();
            game = em.find(Game.class,gameId.getGameId());

            em.remove(game);
            em.flush(); //vad g;r det?
            et.commit();

        }catch(Exception ex){
            System.out.println("Exception remove game");
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            System.out.println("remove game");
            em.close();
        }
    }
    public Game getGame(int gameId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Game game = new Game();

        try{
            et = em.getTransaction();
            et.begin();
            game = em.find(Game.class,gameId);


        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return game;
    }

    public ObservableList<Game> getGameObservableList(){
        return gameObservableList;
    }


}

