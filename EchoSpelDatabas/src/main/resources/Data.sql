USE echo;

INSERT INTO personal (fName,lName,nickName,adress,postal_Nmbr,postal_city,country,e_Mail) VALUES
('Richard','Hendricks','fGavin','retracted',12345,'Woodland Hills','United States','richard@PiedPieper.com'),
('Jian','Yang','newJobs','retracted','12345','Woodland Hills', 'United States', 'Jian-Yang@PiedPieper.com'),
('Erlich','Bachman', 'Aviato', 'unknown',12345,'unkown', 'Tibet/PRC', 'fatMan@PiedPieper.com'),
('Nelson', 'Bighetti','Big Head', 'retracted','12345','unkown','United States','BigHead@PiedPieper.com');

INSERT INTO game(name) VALUES('CSGO'),('Leauge Of Legends'),('Valorant'),('Hunt Showdown'),('Halo Infinite'),('Tetris 99');

INSERT INTO team(name,game_Id) VALUES
('NIP','1'),('NAVI','1'),('Fnatic','2'),('SKT1','2'),('DRX','3'),('100 Thieves','3'),('Grounded','4'),('Lunar','4'),
('Blue','5'),('Red','5');

INSERT INTO player(fName,lName,nickName,adress,postal_Nmbr,postal_city,country,e_Mail,team_Id) VALUES
('Markus','Friberg','Faker','Grusvägen 2','12345','Mordor','Sweden','faker@skt1.com','2'),
('Alfred','Nobel','Dynamit-Harry','retracted','12345','Stockholm','Sweden','Alfred@bofors.com','1'),
('Kalle','Anka','Donald','Disney-Land','12345','Anaheim','United States','Donald@disney.com','3'),
('Arthas','Menethil','Lich-King','ICC','4812','Northend','non-alligned','iceBoy@scourge.com','4'),
('Sven','Svensson','Svenne','Sveavägen 2',12345,'Stockholm','Sweden','sven@live.com','5'),
('Silvia','Renate','TheQueen','Drottningholms Slott','17893','Drottningholm','Sweden','TheQueen@hotmail.com','3');


