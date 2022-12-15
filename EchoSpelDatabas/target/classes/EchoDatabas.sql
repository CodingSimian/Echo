CREATE DATABASE echo;
USE echo;

CREATE TABLE personal(
personal_Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
fName VARCHAR(50) NOT NULL,
lName VARCHAR(50) NOT NULL,
nickName VARCHAR(50) NOT NULL UNIQUE,
adress VARCHAR(50) NOT NULL,
postal_Nmbr INT NOT NULL ,
postal_city VARCHAR(50) NOT NULL,
country VARCHAR(50) NOT NULL,
e_Mail VARCHAR(70) NOT NULL
);


CREATE TABLE PvP(
match_Id INT PRIMARY KEY,
player_Id1 INT,
player_Id2 INT,
game_Id INT,
winner_Id INT,
date DATE,
score_P1 INT,
score_P2 INT,
FOREIGN KEY (player_Id1) REFERENCES player(player_Id),
FOREIGN KEY (player_Id2) REFERENCES player(player_Id),
FOREIGN KEY (game_Id) REFERENCES game(game_Id)
);


CREATE TABLE TvT(
match_Id INT PRIMARY KEY,
team_Id1 INT,
team_Id2 INT,
game_Id INT,
winner_Id INT,
date DATE,
score_T1 INT,
score_T2 INT,
FOREIGN KEY (team_Id1) REFERENCES team(team_Id),
FOREIGN KEY (team_Id2) REFERENCES team(team_Id),
FOREIGN KEY (game_Id) REFERENCES game(game_Id)
);

CREATE TABLE game(
game_Id INT PRIMARY KEY,
name VARCHAR(50)
);


CREATE TABLE team(
team_Id INT PRIMARY KEY,
name VARCHAR(50),
game_Id INT,
FOREIGN KEY (game_Id) REFERENCES game(game_Id)
);


CREATE TABLE player(
player_Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
fName VARCHAR(50) NOT NULL,
lName VARCHAR(50) NOT NULL,
nickName VARCHAR(50) NOT NULL UNIQUE,
adress VARCHAR(50),
postal_Nmbr INT,
postal_city VARCHAR(50),
country VARCHAR(50),
e_Mail VARCHAR(70),
team_Id INT NOT NULL, 
FOREIGN KEY (team_Id) REFERENCES team(team_Id)
);
