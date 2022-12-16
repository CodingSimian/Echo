package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;

import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class GameController {


    public Game getGame(int gameId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Game game = new Game();

        try {
            et = em.getTransaction();
            et.begin();
            game = em.find(Game.class, gameId);

        } catch (Exception ex) {
            System.out.println("Exception get game");
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return game;
    }

    public void addGame(String gameName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Game game = new Game();
            game.setGameName(gameName);
            //game.setPointType(pointsType);
            em.persist(game);
            et.commit();
        } catch (Exception ex) {
            System.out.println("Exception add game");
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            System.out.println("add game");
            em.close();
        }
    }

    public void updateGame(int gameId, String gameName) {

        Game game = new Game();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            game = em.find(Game.class, gameId);
            game.setGameName(gameName);
            //game.setPointType(pointsType);
            em.merge(game);
            et.commit();

        } catch (Exception ex) {
            System.out.println("Exception uppdate game");
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            System.out.println("update game");
            em.close();
        }
    }

    public void removeGame(int gameId) {

        Game game = new Game();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            game = em.find(Game.class, gameId);
            em.remove(game);
            et.commit();
        } catch (Exception ex) {
            System.out.println("Exception remove game");
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            System.out.println("remove game");
            em.close();
        }
    }
}
