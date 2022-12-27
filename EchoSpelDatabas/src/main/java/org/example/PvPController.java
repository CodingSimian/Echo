package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;
import static org.example.Main.ENTITY_MANAGER_FACTORY;
public class PvPController {
    //denna klass ska ge metoder till PvP klassen som möjliggör att ändra på datan i databasen.
    //dessa metoder ska: visa, lägga till, ändra och ta bort matcher (playervs player)

private ObservableList<PvP> PvPList;


//constructor
    public PvPController(){ //Constructorn fungerar som showMatches() men den använder sig av resultList från typedquery som
        //i sin tur fyller PvPList propertien.

        PvPList = FXCollections.observableArrayList();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM PvP c WHERE c.match_Id IS NOT NULL"; //Här är c en placeholder
        TypedQuery<PvP> tq = em.createQuery(strQuery, PvP.class);

        try{
            List<PvP> somePvPMatches = tq.getResultList();
            PvPList.addAll(somePvPMatches);
            System.out.println(PvPList.toString());

        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally{
            em.close();
        }

    }
    public void addPvPMatch( Player daPlayer_Id1,Player daPlayer_Id2, Game daGame_Id, String daDate ){
    //Denna metod ska skapa en PvP match, dock pga karaktären av hur spel fungerar så måste resultatet läggas till
        //efter spelet är spelat. därav kmr score_p1/p2 samt winner_id vara 0 efter denna metod är kallad.
        //Denna metod ska fungera som "INSERT INTO" i sql, sedermera har PvP tabellen foreign keys och pga det
        //så kan man endast inserta värden på de foreign keys om dom redan existerar (du referar till något som ej finns)

        PvP somePvPgame = new PvP();
        somePvPgame.setPlayer_1(daPlayer_Id1);
        somePvPgame.setPlayer_2(daPlayer_Id2);
        somePvPgame.setDate(daDate);
        somePvPgame.setGame_Id(daGame_Id);

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(somePvPgame); //Supposedly fungerar merge istället för persist i detta kontext eftersom
            //I de förra metoderna så stänger man entityManagern.

            //em.merge(somePvPgame);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            em.close();
            PvPList.add(somePvPgame);

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
            somePvPMatches.forEach(cust -> System.out.println(cust.getPlayer_Id1() + " möter " + cust.getPlayer_Id2() + " där match_id är " + cust.getMatch_Id()));
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
            System.out.println(somePvPMatch.getPlayer_Id1() + " möter " + somePvPMatch.getPlayer_Id2() + " där match_id är " + somePvPMatch.getMatch_Id());
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
            System.out.println(somePvPMatch.getPlayer_Id1() + " möter " + somePvPMatch.getPlayer_Id2() + " där match_id är " + somePvPMatch.getMatch_Id()
            + " där vinnaren är\n" + somePvPMatch.getWinner_Name() + " med en poäng av " + somePvPMatch.getScore_P2());
        }
        catch(NoResultException ex){
            System.out.println("ex");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }


    public void deleteMatch(int match_Id){
        //Metod som tar bort en match utifrån en primary key i PvP tabellen
        //Denna kod är nästan 100% kopierad från Marcus Friberg github

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            PvP theMatchToRemove = entityManager.find(PvP.class, match_Id);

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



    public void deleteMatch2(PvP daMatch){
        //Metod som tar bort en match utifrån en primary key i PvP tabellen
        //Denna kod är nästan 100% kopierad från Marcus Friberg github

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        PvPList.remove(daMatch);
        PvP someMatch;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            someMatch = entityManager.find(PvP.class, daMatch.getMatch_Id());

            entityManager.remove(someMatch);
            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();

        }catch (IllegalArgumentException e){ //https://www.baeldung.com/jpa-query-parameters
            //Tills vidare använder sig denna metod utav datum för att separera matcher
            String test = daMatch.getDate();
            String strQuery = "SELECT e FROM Personal e WHERE e.nickName = ?1";
            TypedQuery<PvP> ps = entityManager.createQuery(strQuery, PvP.class);


            someMatch = ps.setParameter(1,test).getSingleResult();
            entityManager.remove(someMatch);//Rad som tar bort ur databasen

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


    public static void changeMatch(String daWinner_ID, int SCORE_P1, int SCORE_P2, int daMatch_Id){
        //Denna metod är till för att ändra på match-poängen. Eftersom man skapar matchen först, sedan efter
        //den är färdiggjord så lägger man till resultatet.

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        PvP somePVPMatch = null;
        try{
            et = em.getTransaction();
            et.begin();
            somePVPMatch = em.find(PvP.class, daMatch_Id);
            somePVPMatch.setWinner_Name(daWinner_ID);
            somePVPMatch.setScore_p1(SCORE_P1);
            somePVPMatch.setScore_P2(SCORE_P2);

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


    public void changeEverythingMatch(PvP matchToBeRemoved,int daMatch_Id,Player daPlayer_Id1,Player daPlayer_Id2, Game daGame_Id, String daDate,String daWinner_ID, int SCORE_P1, int SCORE_P2){
        //Denna metod är till för att ändra på match-poängen. Eftersom man skapar matchen först, sedan efter
        //den är färdiggjord så lägger man till resultatet.

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        PvPList.remove(matchToBeRemoved); //Brute force lösning men alternativet är att börja med multithreading
        PvP somePVPMatch;
        try{
            et = em.getTransaction();
            et.begin();
            somePVPMatch = em.find(PvP.class, daMatch_Id);
            somePVPMatch.setWinner_Name(daWinner_ID);
            somePVPMatch.setScore_p1(SCORE_P1);
            somePVPMatch.setScore_P2(SCORE_P2);

            somePVPMatch.setPlayer_1(daPlayer_Id1);
            somePVPMatch.setPlayer_2(daPlayer_Id2);
            somePVPMatch.setDate(daDate);
            somePVPMatch.setGame_Id(daGame_Id);

            em.merge(somePVPMatch); //merge är som persist metoden fast du returnerar en kopia istället för det du skickade in som inparameter
            //em.persist(somePVPMatch);
            et.commit();
            PvPList.add(somePVPMatch);
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

    public void updateInfoPvPMatch(PvP matchSelected){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        PvPList.remove(matchSelected);
        PvP someMatch;
        try{
            et = em.getTransaction();
            et.begin();

            someMatch = em.find(PvP.class,matchSelected.getMatch_Id());

            someMatch.updateMatch(matchSelected.getWinner_Name(),matchSelected.getScore_P1(),matchSelected.getScore_P2());

            em.merge(someMatch);
            et.commit();
            PvPList.add(someMatch);


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

    public ObservableList<PvP> getPvPList() {
        return PvPList;
    }
}
