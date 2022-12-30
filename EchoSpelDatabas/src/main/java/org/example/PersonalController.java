package org.example;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class PersonalController {

    private ObservableList<Personal> personal ;


    public PersonalController(){
        personal = FXCollections.observableArrayList();
       uppdatePersonalList();
    }

    public void uppdatePersonalList(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Personal e WHERE e.id IS NOT NULL";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
        personal.clear();


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
        String strQuery = "SELECT e FROM Personal e WHERE e.nickName=  :nick";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
        ps.setParameter("nick",pers.getNickName());

        try{
            et = em.getTransaction();
            et.begin();
            em.persist(pers);
            et.commit();
            pers = ps.getSingleResult();
            personal.add(pers);


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

    public void updatePersonal(Personal temp){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        personal.remove(temp);
        Personal pers;
        try{
            et = em.getTransaction();
            et.begin();
             pers  = em.find(Personal.class,temp.getId());
             pers.uppDatePersonalInfo(temp.getFirstName(), temp.getLastName(), temp.getNickName(),temp.getAdress(),temp.getPostalNumber(),temp.getPostalCity(),temp.getCountry(),temp.getEmail());
             em.merge(pers);
            et.commit();
            personal.add(pers);
            System.out.println(personal.size());
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
        String strQuery = "SELECT e FROM Personal e WHERE e.nickName=  :nick";
        TypedQuery<Personal> ps = em.createQuery(strQuery, Personal.class);
        ps.setParameter("nick",person.getNickName());

        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,person.getId());
            em.remove(pers);
            et.commit();


        }catch (IllegalArgumentException e){
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
    public ObservableList<Personal> getPersonal(){
        return personal;
    }
}
