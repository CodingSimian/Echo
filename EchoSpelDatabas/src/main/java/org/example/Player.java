package org.example;

import javax.persistence.*;
import java.io.Serializable;

import static java.sql.Types.NULL;

@Entity
public class Player implements Serializable {

  public void player() {
  }

  @Id
  @Column(name = "player_Id")
  private int player_Id;

  @Column(name = "fName")
  private String fName;

  @Column(name = "lName")
  private String lName;

  @Column(name = "nickName")
  private String nickName;

  @Column(name = "adress")
  private String adress;

  @Column(name = "postal_Nmbr")
  private int Postal_Numbr;

  @Column(name = "postal_city")
  private String Postal_city;

  @Column(name = "country")
  private String country;

  @Column(name = "e_mail")
  private String e_mail;

  @Column(name = "team_Id")
  private Integer  team_Id;


  public int getPlayer_Id() {
   return player_Id;
  }

  public void setPlayer_Id(int player_Id) {
   this.player_Id = player_Id;
  }

  public String getfName() {
   return fName;
  }

  public void setfName(String fName) {
   this.fName = fName;
  }

  public String getlName() {
   return lName;
  }

  public void setlName(String lName) {
   this.lName = lName;
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

  public int getPostal_Numbr() {
   return Postal_Numbr;
  }

  public void setPostal_Numbr(int postal_Numbr) {
   Postal_Numbr = postal_Numbr;
  }

  public String getPostal_city() {
   return Postal_city;
  }

  public void setPostal_city(String postal_city) {
   Postal_city = postal_city;
  }

  public String getCountry() {
   return country;
  }

  public void setCountry(String country) {
   this.country = country;
  }

  public String getE_mail() {
   return e_mail;
  }

  public void setE_mail(String e_mail) {
   this.e_mail = e_mail;
  }

  public int getTeam_Id() {
   return team_Id;
  }


  public void setTeam_Id(Integer team_Id) {
   this.team_Id = team_Id;
  }
  public void setTeam_IdNull(){
      this.team_Id = null;
  }

  public void player_Id(){

  }

  @Override
    public String toString(){
      return this.nickName;
  }
 }
