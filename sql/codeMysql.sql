create database if not exists TropikHotel;
use TropikHotel;

CREATE TABLE if not exists CLIENTS (
  NumClient INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomClient VARCHAR(50) NOT NULL,
  AddressClient VARCHAR(100),
  CpClient VARCHAR(10),
  PaysClient VARCHAR(20),
  TelClient VARCHAR(20),
  EmailClient VARCHAR(50)
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
  Heure TIME,
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
  NomType VARCHAR(20)  NOT NULL
);

DESC TYPES;

CREATE TABLE if not exists CHAMBRES (
  NomChambre VARCHAR(50) NOT NULL PRIMARY KEY,
  TelChambre VARCHAR(20) NOT NULL,
  EtageChambre VARCHAR(10) NOT NULL,
  OccupeChambre VARCHAR(20) NOT NULL,
  ChauffeauChambre VARCHAR(20) NOT NULL,
  PrixChambre INT,
  NumCategorie INT,
  NumType INT,
  constraint FkCatCha foreign key (NumCategorie) references CATEGORIES(NumCategorie),
  constraint FkTypCha foreign key (NumType) references TYPES(NumType)
);


CREATE TABLE if not exists REGLEMENTS (
  NumReglement INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  MontantReglement INT,
  EtatReglement VARCHAR(20)
);

DESC REGLEMENTS;


DESC CHAMBRES;

CREATE TABLE if not exists FACTURE (
  NumFacture INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  DateFacture DATE NOT NULL,
  MontantFacture INT NOT NULL,
  NumReglement INT,
  NumClient INT,
  constraint FkRegFct foreign key (NumReglement) references REGLEMENTS(NumReglement),
  constraint FkCliFct foreign key (NumClient) references CLIENTS(NumClient)
);

DESC FACTURE;


CREATE TABLE if not exists RESERVER (
  NumReservation INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  DateDebutReservation DATE,
  DateFinReservation DATE,
  NbJourReservation INT,
  EtatReservation VARCHAR(20),
  NumClient INT,
  NumResponsable INT,
  NumReglement INT,
  constraint FkRegRes foreign key (NumReglement) references REGLEMENTS(NumReglement),
  constraint FkCliRes foreign key (NumClient) references CLIENTS(NumClient),
  constraint FkRspRes foreign key (NumResponsable) references RESPONSABLES(NumResponsable)
);

DESC RESERVER;

CREATE TABLE if not exists CONCERNER (
  NumReservation INT,
  NomChambre VARCHAR(50),
  constraint FkResCon foreign key (NumReservation) references RESERVER(NumReservation),
  constraint FkChaCon foreign key (NomChambre) references CHAMBRES(NomChambre)
);

DESC CONCERNER;



CREATE TABLE if not exists COMMANDER (
  NumCommander INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  TarifCommander INT,
  DateCommander VARCHAR(20),
  NumClient INT,
  NumRepas INT,
  constraint FkCliCom foreign key (NumClient) references CLIENTS(NumClient),
  constraint FkRepCom foreign key (NumRepas) references REPAS(NumRepas)
);

DESC COMMANDER;

INSERT INTO CLIENTS (NomClient, AddressClient, CpClient, PaysClient, TelClient, EmailClient) VALUES
("Nantenaina","ambalavao","303","Madagascar","0343949863","rasolondraibeandrianantenaina@gmail.com");

INSERT INTO RESPONSABLES (NomResponsable, PrenomResponsable, PseudoResponsable, PasswordResponsable, AddressResponsable, TelResponsable,DroitResponsable,ImageUrlResponsable) VALUES ("RASOLONDRAIBE", "Andrianantenaina", "blackran", "iloveyou","ambohitrandriana ambalavao", "0343949863","SUPERS","/home/blackran/Desktop/Me.jpg"),
("RASOLONDRAIBE", "Feno Sitraka", "root", "password","ambohitrandriana ambalavao",NULL,"USERS","/home/blackran/Desktop/sitraka.jpg");

SELECT * FROM RESPONSABLES;

INSERT INTO CATEGORIES (DescriptionCategorie) VALUES
("bengalo"),
("chambre");

SELECT * FROM CATEGORIES;

INSERT INTO TYPES (NomType)VALUES
("simple"),
("double");

SELECT * FROM TYPES;

INSERT INTO CHAMBRES (NomChambre, TelChambre, EtageChambre, OccupeChambre, ChauffeauChambre, PrixChambre, NumCategorie,NumType) VALUES
("ROSE","0349354341","1","1","GAZ",20000,1,1),
("1","0340022211","0","0","ELECTRIQUE",30000,1,1)
;

SELECT * FROM CHAMBRES;

insert into REGLEMENTS(EtatReglement, MontantReglement) values ('non regler', 0);

SELECT * FROM REGLEMENTS;

INSERT INTO RESERVER (DateDebutReservation,DateFinReservation,NbJourReservation,EtatReservation,NumClient,NumResponsable, NumReglement)
VALUES("2019-01-02","2019-01-03",1,"0",1,1,1);

SELECT * FROM RESERVER;
