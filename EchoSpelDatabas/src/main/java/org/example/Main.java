package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    public static void main(String[] args) {
        TeamMatch teamMatch = new TeamMatch();
        TeamMatchController controller = new TeamMatchController();

     /*   teamMatch = controller.getMatch(1);
        System.out.println(teamMatch.getTeamId2()); */
        controller.addTeamMatch(1,2,1,2,"2022-12-23",1,1);

        ENTITY_MANAGER_FACTORY.close();

    }
}