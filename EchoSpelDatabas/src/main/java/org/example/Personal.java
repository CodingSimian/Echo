package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Personal {

    // properties
    @Id
    @Column(name = "personal_Id")
    private int id;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "lName")
    private String lastName;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "adress")
    private String adress;
    @Column(name = "postal_Nmbr")
    private int postalNumber;
    @Column(name = "postal_city")
    private String postalCity;
    @Column(name = "country")
    private String country;
    @Column(name = "e_Mail")
    private String email;

    public int getId() {
        return id;
    }

    // Getters n Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPostalNumber(){
        return postalNumber;
    }

    public void setPostalNumber(int postalNnumber) {
        this.postalNumber = postalNnumber;
    }

    public String getPostalCity() {
        return postalCity;
    }

    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Konstruktor 1
    public Personal(String firstName,String lastName, String nickName, String  adress, int postalNnumber, String postalCity, String country, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.adress = adress;
        this.postalNumber = postalNnumber;
        this.postalCity = postalCity;
        this.country = country;
        this.email = email;
    }
    // Konstruktor 2
    public Personal(){}

    // Uppdatera info
    public void uppDatePersonalInfo(String firstName,String lastName, String nickName, String  adress, int postalNnumber, String postalCity, String country, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.adress = adress;
        this.postalNumber = postalNnumber;
        this.postalCity = postalCity;
        this.country = country;
        this.email = email;
    }


}
