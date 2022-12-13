package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    public static void main(String[] args) {
        TeamMatch teamMatch = new TeamMatch();
        TeamMatchController controller = new TeamMatchController();
        List<TeamMatch> teamMatchList = null;

        teamMatch = controller.getTeamMatch(4);
        System.out.println(teamMatch.getTeamId2());
      //  controller.addTeamMatch(1,2,1,2,"2022-12-23",1,1);
      // teamMatchList =  controller.getAllMatches();
      //  controller.getTeamMatch(3);
      //  controller.removeTeamMatch(1);


        ENTITY_MANAGER_FACTORY.close();

    }
}