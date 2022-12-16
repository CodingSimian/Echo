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

public class PersonalController {

    private ObservableList<Personal> personal ;


    public PersonalController(){
        personal = FXCollections.observableArrayList();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Personal e WHERE e.id IS NOT NULL";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);



        try{
            List<Personal> temp = ps.getResultList();
            personal.addAll(temp);

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }


    }


    public  void addPersonal(String fName, String lName, String nickName, String adress, int postalnNumber, String postalCity, String country, String email ){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Personal pers = new Personal(fName,lName,nickName,adress,postalnNumber,postalCity,country,email); // TODO så att tillagda personal hämtar sitt id från databsen;



        try{
            et = em.getTransaction();
            et.begin();
            em.persist(pers);
            et.commit();


        }catch(Exception ex){
            System.out.println("Finnaly");
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            System.out.println("finally");
            em.close();
            personal.add(pers);
        }
    }

    public void updatePersonal(Personal temp){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personal.remove(temp);
        Personal database ;



        try{
            et = em.getTransaction();
            et.begin();
            database = em.find(Personal.class,temp.getId());
            database.uppDatePersonalInfo(temp.getFirstName(),temp.getLastName(),temp.getNickName(),temp.getAdress(), temp.getPostalNumber(), temp.getPostalCity(),temp.getCountry(),temp.getEmail());
            em.merge(database);
            et.commit();
            personal.add(database);


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

    public  void removePersonal(Personal person){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personal.remove(person);
        Personal pers ;
        String text = person.getNickName();
        String strQuery = "SELECT e FROM Personal e WHERE e.nickName=  :nick";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
        ps.setParameter("nick",person.getNickName());
        TypedQuery<Personal> ps2 = em.createQuery(strQuery,Personal.class);


        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,person.getId());
            em.remove(pers);
            et.commit();


        }catch (IllegalArgumentException e){
           // String test = person.getNickName();
           // String strQuery = "SELECT e FROM Personal e WHERE e.nickName=:"+ test;
            //TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
            pers = ps.getSingleResult();
            em.remove(pers);
            et.commit();

        }
        catch(Exception ex){
            if(et != null){
                et.rollback();
            }

        }
        finally {
            em.close();
        }

    }

    public void getTeamMembers(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        //TODO när Spelare klassen är kopplad gör klart metod för att hämta alla spelare med motsvarande teamId
    }

    public Personal getPersonal(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Personal pers = new Personal();

        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,id);


        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return pers;

    }

    public List<Personal> getAllPersonal(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Personal e WHERE e.id IS NOT NULL";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
        List<Personal> temp = new ArrayList<>();


        try{
            temp = ps.getResultList();
            personal.addAll(temp);
            System.out.println(personal.toString());

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return temp;


    }
    public ObservableList<Personal> getPersonal1(){
        return personal;
    }
}
