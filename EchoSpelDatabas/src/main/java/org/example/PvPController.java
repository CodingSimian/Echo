package org.example;

import javax.persistence.*;
import java.util.List;
import static org.example.Main.ENTITY_MANAGER_FACTORY;
public class PvPController {
    //denna klass ska ge metoder till PvP klassen som möjliggör att ändra på datan i databasen.
    //dessa metoder ska: visa, lägga till, ändra och ta bort matcher (playervs player)


    public static void addPvPMatch(int daMatch_Id, int daPlayer_Id1,int daPlayer_Id2, int daGame_Id, String daDate ){
    //Denna metod ska skapa en PvP match, dock pga karaktären av hur spel fungerar så måste resultatet läggas till
        //efter spelet är spelat. därav kmr score_p1/p2 samt winner_id vara 0 efter denna metod är kallad.
        //Denna metod ska fungera som "INSERT INTO" i sql, sedermera har PvP tabellen foreign keys och pga det
        //så kan man endast inserta värden på de foreign keys om dom redan existerar (du referar till något som ej finns)

        PvP somePvPgame = new PvP();
        somePvPgame.setMatch_Id(daMatch_Id);
        somePvPgame.setPlayer_id1(daPlayer_Id1);
        somePvPgame.setPlayer_id2(daPlayer_Id2);
        somePvPgame.setDate(daDate);
        somePvPgame.setGame_Id(daGame_Id);

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(somePvPgame);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            em.close();
        }
        System.out.println(isSuccess);

    }

    public static void showMatches(){
        //Metod som ska visa alla matcher

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM PvP c WHERE c.match_Id IS NOT NULL"; //Här är c en placeholder
        TypedQuery<PvP> tq = em.createQuery(strQuery, PvP.class);
        List<PvP> somePvPMatches;
        try{
            somePvPMatches = tq.getResultList();
            somePvPMatches.forEach(cust -> System.out.println(cust.getPlayer_id1() + " möter " + cust.getPlayer_id2() + " där match_id är " + cust.getMatch_Id()));
            //cust blir en placeholder här oxå

        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally{
            em.close();
        }
    }

    public static void getMatch(int daMatch_Id){
        //Metod som ska visa en specifik match genom att använda sig av de primary keys som finns i PvP tabellen
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM PvP c WHERE c.match_Id= :match_Id"; //c är placeholder
        TypedQuery<PvP> tq = em.createQuery(query, PvP.class);
        tq.setParameter("match_Id", daMatch_Id);


        PvP somePvPMatch = null;
        try{
            somePvPMatch = tq.getSingleResult();
            System.out.println(somePvPMatch.getPlayer_id1() + " möter " + somePvPMatch.getPlayer_id2() + " där match_id är " + somePvPMatch.getMatch_Id());
        }
        catch(NoResultException ex){
            System.out.println("ex");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }


    public static void getMatchAndMore(int daMatch_Id){
        //Metod som ska visa en specifik match genom att använda sig av de primary keys som finns i PvP tabellen
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM PvP c WHERE c.match_Id= :match_Id"; //c är placeholder
        TypedQuery<PvP> tq = em.createQuery(query, PvP.class);
        tq.setParameter("match_Id", daMatch_Id);


        PvP somePvPMatch = null;
        try{
            somePvPMatch = tq.getSingleResult();
            System.out.println(somePvPMatch.getPlayer_id1() + " möter " + somePvPMatch.getPlayer_id2() + " där match_id är " + somePvPMatch.getMatch_Id()
            + " där vinnaren är\n" + somePvPMatch.getWinner_Id() + " med en poäng av " + somePvPMatch.getScore_p2());
        }
        catch(NoResultException ex){
            System.out.println("ex");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }


    public static void deleteMatch(int match_Id){
        //Metod som tar bort en match utifrån en primary key i PvP tabellen
        //Denna kod är nästan 100% kopierad från Marcus Friberg github

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            PvP theMatchToRemove = entityManager.find(PvP.class, match_Id);
            // Call remove-method of the EntityManager on the rental-entity passed to the method to remove it
            // from the managed objects.
            entityManager.remove(theMatchToRemove);
            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        System.out.println(isSuccess);
    }

    public static void changeMatch(int daWinner_ID, int SCORE_P1, int SCORE_P2, int daMatch_Id){
        //Denna metod är till för att ändra på match-poängen. Eftersom man skapar matchen först, sedan efter
        //den är färdiggjord så lägger man till resultatet.

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        PvP somePVPMatch = null;
        try{
            et = em.getTransaction();
            et.begin();
            somePVPMatch = em.find(PvP.class, daMatch_Id);
            somePVPMatch.setWinner_Id(daWinner_ID);
            somePVPMatch.setScore_p1(SCORE_P1);
            somePVPMatch.setScore_p2(SCORE_P2);

            em.persist(somePVPMatch);
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

}
