package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.ENTITY_MANAGER_FACTORY;

public class PersonalController {


    public  void addPersonal(String fName, String lName, String nickName, String adress, int postalnNumber, String postalCity, String country, String email ){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Personal pers = new Personal(fName,lName,nickName,adress,postalnNumber,postalCity,country,email);



        try{
            et = em.getTransaction();
            et.begin();
            em.persist(pers);
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

    public void updatePersonal(int id, String firstName, String lastName, String nickName, String adress, int postalnNumber, String postalCity, String country, String email ){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;


        try{
            et = em.getTransaction();
            et.begin();
            Personal pers ;
            pers= em.find(Personal.class,id);
            pers.uppDatePersonalInfo(firstName,lastName,nickName,adress,postalnNumber,postalCity,country,email);
            em.merge(pers);
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

    public  void removePersonal(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Personal pers ;

        try{
            et = em.getTransaction();
            et.begin();
            pers = em.find(Personal.class,id);
            em.remove(pers);
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
        List<Personal> personalLista = new ArrayList<>();

        try{
           personalLista = ps.getResultList();

        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return personalLista;

    }
}
