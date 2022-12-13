package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}