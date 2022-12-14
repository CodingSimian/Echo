package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
public class Main {
    protected static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    public static void main(String[] args) {

        PvPController thePlayerMatchController = new PvPController();
        //thePlayerMatchController.addPvPMatch(3,1,2,1, "2022-12-13");
        thePlayerMatchController.getMatchAndMore(1);
        thePlayerMatchController.changeMatch(1, 30, 15,3);
        thePlayerMatchController.getMatchAndMore(3);

        //thePlayerMatchController.deleteMatch(3);
        //thePlayerMatchController.showMatches();

    }
}