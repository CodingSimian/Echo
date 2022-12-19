package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class PersonalController {

    private ObservableList<Personal> personalObservableList; //Observable list används eftersom den ändrar sin grafiska aspekt så fort dess värden förändras


    public PersonalController(){
        personalObservableList = FXCollections.observableArrayList();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Personal e WHERE e.id IS NOT NULL";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);



        try{
            List<Personal> temp = ps.getResultList();
            personalObservableList.addAll(temp);
            System.out.println(personalObservableList.toString());

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
            personalObservableList.add(pers);
        }
    }

    public void updatePersonal(Personal temporary ){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personalObservableList.remove(temporary);
        Personal database ;
        try{
            et = em.getTransaction();
            et.begin();

            database = em.find(Personal.class,temporary.getId());
            database.uppDatePersonalInfo(temporary.getFirstName(),temporary.getLastName(),temporary.getNickName(),temporary.getAdress(),temporary.getPostalNumber(),temporary.getPostalCity(),temporary.getCountry(),temporary.getEmail());
            em.merge(database);
            et.commit();
            personalObservableList.add(database);


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

    public  void removePersonal2(Personal person){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personalObservableList.remove(person); //Rad som tar bort person ur observable list
        Personal pers;

        /*String strQuery = "SELECT e FROM Personal e WHERE e.nickName=:"+ person.getNickName();
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);*/


        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,person.getId());
            em.remove(pers);

            em.flush();

            et.commit();

        }catch (IllegalArgumentException e){ //https://www.baeldung.com/jpa-query-parameters
            String test = person.getNickName();
            String strQuery = "SELECT e FROM Personal e WHERE e.nickName = ?1";
            TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);


            pers = ps.setParameter(1,test).getSingleResult();
            em.remove(pers);//Rad som tar bort ur databasen

            em.flush();

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


    public  void removePersonal(Personal person){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personalObservableList.remove(person);
        Personal pers;

        /*String strQuery = "SELECT e FROM Personal e WHERE e.nickName=:"+ person.getNickName();
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);*/


        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,person.getId());
            em.remove(pers);
            et.commit();


        }catch (IllegalArgumentException e){
            String test = person.getNickName();
            String strQuery = "SELECT e FROM Personal e WHERE e.nickName=" + test;
            TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
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
            personalObservableList.addAll(temp);
            System.out.println(personalObservableList.toString());

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return temp;


    }
    public ObservableList<Personal> getPersonal1(){
        return personalObservableList;
    }
}
