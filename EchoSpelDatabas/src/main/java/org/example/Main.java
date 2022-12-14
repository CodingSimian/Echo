package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main extends Application{
    protected static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Echo");
    static Graphics graphics;

    public static void main(String[] args) throws IOException {
          graphics = new Graphics();
         launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage = graphics.start();
        stage.show();
    }
}