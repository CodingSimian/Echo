module EchoSpelDatabas {

    requires javafx.graphics;
    requires javafx.controls;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;

    opens org.example;
    exports org.example;
}