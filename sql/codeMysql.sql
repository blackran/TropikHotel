create database if not exists TropikHotel;
use TropikHotel;

CREATE TABLE if not exists CLIENTS (
  NumClient INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomClient VARCHAR(50) NOT NULL,
  AddressClient VARCHAR(100),
  CpClient VARCHAR(10),
  PaysClient VARCHAR(20),
  TelClient VARCHAR(20),
  EmailClient VARCHAR(50),
  AnneeCreClient VARCHAR(20)
);

DESC CLIENTS;

CREATE TABLE if not exists RESPONSABLES (
  NumResponsable INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomResponsable VARCHAR(50) NOT NULL,
  PseudoResponsable VARCHAR(50) NOT NULL,
  PasswordResponsable VARCHAR(50) NOT NULL,
  PrenomResponsable VARCHAR(50),
  AddressResponsable VARCHAR(100) NOT NULL,
  TelResponsable VARCHAR(20),
  DroitResponsable VARCHAR(10),
  ImageUrlResponsable VARCHAR(200)
);

DESC RESPONSABLES;


CREATE TABLE if not exists REPAS (
  NumRepas INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomRepas VARCHAR(50),
  CategorieRepas VARCHAR(50),
  PrixRepas INT
);

DESC REPAS;

CREATE TABLE if not exists CATEGORIES (
  NumCategorie INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  DescriptionCategorie VARCHAR(50)  NOT NULL
);

DESC CATEGORIES;

CREATE TABLE if not exists TYPES (
  NumType INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomType VARCHAR(20)  NOT NULL,
  DescriptionType VARCHAR(100)
);

DESC TYPES;

CREATE TABLE if not exists CHAMBRES (
  NomChambre VARCHAR(50) NOT NULL PRIMARY KEY,
  TelChambre VARCHAR(20) NOT NULL,
  EtageChambre VARCHAR(10) NOT NULL,
  ChauffeauChambre VARCHAR(20) NOT NULL,
  PrixChambre INT,
  NumCategorie INT,
  NumType INT,
  constraint FkCatCha foreign key (NumCategorie) references CATEGORIES(NumCategorie) ON DELETE SET NULL ON UPDATE CASCADE,
  constraint FkTypCha foreign key (NumType) references TYPES(NumType) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE if not exists REGLEMENTS (
  NumReglement INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  MontantReglement INT,
  EtatReglement VARCHAR(20),
  AnneeReglement VARCHAR(20)
);

DESC REGLEMENTS;


CREATE TABLE if not exists RESERVER (
  NumReservation INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  DateDebutReservation DATE,
  DateFinReservation DATE,
  NbJourReservation INT,
  EtatReservation VARCHAR(20),
  ConditionReservation VARCHAR(100),
  NumClient INT,
  NumResponsable INT,
  NumReglement INT,
  constraint FkRegRes foreign key (NumReglement) references REGLEMENTS(NumReglement) ON DELETE SET NULL ON UPDATE CASCADE,
  constraint FkCliRes foreign key (NumClient) references CLIENTS(NumClient) ON DELETE SET NULL ON UPDATE CASCADE,
  constraint FkRspRes foreign key (NumResponsable) references RESPONSABLES(NumResponsable) ON DELETE SET NULL ON UPDATE CASCADE
);

DESC RESERVER;

CREATE TABLE if not exists CONCERNER (
  NumReservation INT,
  NomChambre VARCHAR(50),
  constraint FkResCon foreign key (NumReservation) references RESERVER(NumReservation) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint FkChaCon foreign key (NomChambre) references CHAMBRES(NomChambre) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC CONCERNER;



CREATE TABLE if not exists COMMANDER (
  NumCommander INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  TarifCommander INT,
  DateCommander VARCHAR(20),
  NumClient INT,
  NumRepas INT,
  constraint FkCliCom foreign key (NumClient) references CLIENTS(NumClient)  ON DELETE SET NULL ON UPDATE CASCADE,
  constraint FkRepCom foreign key (NumRepas) references REPAS(NumRepas)  ON DELETE SET NULL ON UPDATE CASCADE
);

DESC COMMANDER;

INSERT INTO CLIENTS (NomClient, AddressClient, CpClient, PaysClient, TelClient, EmailClient, AnneeCreClient) VALUES
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2000"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2000"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2001"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2002"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2002"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2002"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2003"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2004"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2005"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2005"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2005"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2006"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2007"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2008"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2009"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2010"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2011"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2012"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2013"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2014"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2015"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2016"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2017"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2018"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019"),
-- ("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019"),
("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com","2019")
;
SELECT * FROM CLIENTS;

INSERT INTO RESPONSABLES (NomResponsable, PrenomResponsable, PseudoResponsable, PasswordResponsable, AddressResponsable, TelResponsable,DroitResponsable,ImageUrlResponsable) VALUES ("RASOLONDRAIBE", "Andrianantenaina", "blackran", "iloveyou","ambohitrandriana ambalavao", "0343949863","SUPERS","/home/blackran/Desktop/Me.jpg"),
("RASOLONDRAIBE", "Feno Sitraka", "root", "password","ambohitrandriana ambalavao",NULL,"USERS","/home/blackran/Desktop/sitraka.jpg");

SELECT * FROM RESPONSABLES;

INSERT INTO CATEGORIES (DescriptionCategorie) VALUES
("bengalo"),
("chambre");

SELECT * FROM CATEGORIES;

INSERT INTO TYPES (NomType)VALUES
("Simple"),
("Double"),
("Double single"),
("Triple"),
("Familiale");

SELECT * FROM TYPES;

INSERT INTO CHAMBRES (NomChambre, TelChambre, EtageChambre, ChauffeauChambre, PrixChambre, NumCategorie,NumType) VALUES
("Coquelicot","0349354341","1","GAZ",20000,1,1),
("Jonquille","0349354341","1","GAZ",20000,1,1),
("Tulipe","0349354341","1","GAZ",20000,1,1),
("Camélia","0349354341","1","GAZ",20000,1,1),

("Jacinthe","0349354341","2","GAZ",20000,1,1),
("Arum","0349354341","2","GAZ",20000,1,1),
("Pic","0349354341","2","GAZ",20000,1,1),
("Muguet","0349354341","2","GAZ",20000,1,1),
("Pivoine","0349354341","2","GAZ",20000,1,1),
("Fuschia","0349354341","2","GAZ",20000,1,1),

("Pervenche","0340022211","1","ELECTRIQUE",30000,1,1),
("Mimosa","0340022211","1","ELECTRIQUE",30000,1,1),
("Capucine","0340022211","1","ELECTRIQUE",30000,1,1),
("Lavande","0340022211","1","ELECTRIQUE",30000,1,1),
("Tournesol","0340022211","1","ELECTRIQUE",30000,1,1),
("Dalhia","0340022211","1","ELECTRIQUE",30000,1,1),
("Iris","0340022211","1","ELECTRIQUE",30000,1,1),
("Myosotis","0340022211","1","ELECTRIQUE",30000,1,1),
("Azalée","0340022211","1","ELECTRIQUE",30000,1,1),

("Rose","0340022211","2","ELECTRIQUE",30000,1,1),
("Glaieul","0340022211","2","ELECTRIQUE",30000,1,1),
("Hortensia","0340022211","2","ELECTRIQUE",30000,1,1),
("Acacias","0340022211","2","ELECTRIQUE",30000,1,1),
("Oeillet","0340022211","2","ELECTRIQUE",30000,1,1)
;

SELECT * FROM CHAMBRES;

insert into REGLEMENTS(EtatReglement, MontantReglement, AnneeReglement) values
-- ('', 22000, "2009"),
-- ('', 10000, "2009"),
-- ('payer', 34000, "2010"),
-- ('payer', 0, "2010"),
-- ('payer', 15000, "2011"),
-- ('', 10000, "2011"),
-- ('', 20000, "2012"),
-- ('', 45000, "2012"),
-- ('', 30000, "2013"),
-- ('', 45000, "2013"),
-- ('payer', 45000, "2014"),
-- ('', 20000, "2014"),
-- ('payer', 20000, "2015"),
-- ('payer', 30000, "2015"),
-- ('payer', 45000, "2016"),
-- ('payer', 30000, "2016"),
-- ('payer', 30000, "2017"),
-- ('payer', 30000, "2017"),
-- ('payer', 50000, "2018"),
-- ('payer', 50000, "2018"),
-- ('payer', 10000, "2019"),
-- ('payer', 20000, "2019"),
-- ('payer', 45000, "2019"),
-- ('payer', 30000, "2019"),
-- ('payer', 45000, "2019"),
('payer', 50000, "2019");

SELECT * FROM REGLEMENTS;

INSERT INTO RESERVER (DateDebutReservation, DateFinReservation, NbJourReservation, ConditionReservation, EtatReservation,NumClient,NumResponsable, NumReglement)
VALUES("2019-01-02","2019-01-03",1,"Diner et dejener","0",1,1,1);

SELECT * FROM RESERVER;
