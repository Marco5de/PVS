/* *************************************************************** */
/* Die Relationen dieses Schemas werden in den SQL-Beispielen      */
/* von Kapitel 2 ("SQL I") der Vorlesung "Informationssysteme      */
/* verwendet. Sie sind eine vereinfachte Version der in den        */
/* Kapitel 3 und 5 der Vorlesung zum Einsatz kommenden Relationen. */
/*-----------------------------------------------------------------*/
/*                Autor: Peter Dadam, März 2013                    */
/*                      Stand: 2017-05-15                          */
/*             Kommentar: Fixes für MySQL eingefügt                */
/*             Kommentar: DROP TABLE und Datum an MySQL angepasst  */
/* *************************************************************** */

/*---------------------------------------------------------------------------------*/
/*                        DROP TABLE Statements                                    */
/*---------------------------------------------------------------------------------*/

DROP DATABASE IF EXISTS legotrailerdb;
CREATE DATABASE legotrailerdb;
USE legotrailerdb;

CREATE TABLE IF NOT EXISTS Abteilungen (Temp INTEGER);
ALTER TABLE Abteilungen DROP FOREIGN KEY IF EXISTS MgrPersNr;

DROP TABLE IF EXISTS Sonderpreise;
DROP TABLE IF EXISTS AuftragsPos;
DROP TABLE IF EXISTS Auftraege;
DROP TABLE IF EXISTS Preisliste;
DROP TABLE IF EXISTS Kunden;
DROP TABLE IF EXISTS BestellPos;
DROP TABLE IF EXISTS Bestellungen;
DROP TABLE IF EXISTS Liefert;
DROP TABLE IF EXISTS Lieferanten;
DROP TABLE IF EXISTS Stueckliste;
DROP TABLE IF EXISTS Teile;
DROP TABLE IF EXISTS Farbcodes;
DROP TABLE IF EXISTS TeileTypen;
DROP TABLE IF EXISTS Mitarbeiter;
DROP TABLE IF EXISTS Abteilungen;




CREATE TABLE TeileTypen (
  TeileID    VARCHAR(8) NOT NULL,
  TeileName  VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (TeileID));

INSERT INTO TeileTypen VALUES
('AAG',   'Aufbauanhänger geschlossen'),                        
('AKK',   'Kippanhänger kurz'),                                 
('AKL',   'Kippanhänger lang'),
('CAH',   'Chassis Aufbauanhänger'),                            
('CKK',   'Chassis Kippanhänger kurz'),                         
('CKL',   'Chassis Kippanhänger lang'),                         
('FA',    'Felge mit Achse'),                                   
('FG',    'Fahrgestell'),                                       
('GO25',  'Gelenkplatte 2 x 5 oben'),                           
('GU25',  'Gelenkplatte 2 x 5 unten'),                          
('GV25',  'Gelenkverbinder 2 x 5'),                             
('K14',   'Klotz 1 x 4'),                                       
('K16',   'Klotz 1 x 6'),                                       
('K18',   'Klotz 1 x 8'),                                       
('K28',   'Klotz 2 x 8'),                                       
('KR24',  'Klotz 2 x 4 für Rad'),                               
('P18',   'Platte 1 x 8'),                                      
('P24',   'Platte 2 x 4'),                                      
('P616',  'Platte 6 x 16'),                                     
('P624',  'Platte 6 x 24'),                                     
('PZ',    'Platte mit Zapfen'),                                 
('RF',    'Reifen'),                                            
('RK11',  'Rundklotz 1 x 1'),                                   
('TL134', 'Tür links 1 x 3 x 4'),                               
('TR134', 'Tür rechts 1 x 3 x 4');


CREATE TABLE Farbcodes (
  Farbcode   INTEGER NOT NULL,
  FarbeText  VARCHAR(10) NOT NULL UNIQUE,
  PRIMARY KEY (Farbcode) );

INSERT INTO Farbcodes VALUES
(0, 'neutral'),
(1, 'rot'),
(2, 'blau');


CREATE TABLE Teile(
  TeileID     VARCHAR(8) NOT NULL,
  Farbe       INTEGER NOT NULL,
  KalkKosten  DECIMAL(6,2),
  Bestand     INTEGER,
  MinBestand  INTEGER,
  PRIMARY KEY (TeileID, Farbe),
  FOREIGN KEY (TeileID) REFERENCES TeileTypen(TeileID),
  FOREIGN KEY (Farbe) REFERENCES Farbcodes(Farbcode) );

INSERT INTO Teile VALUES
('AAG',    1, 342.2,  5,  0),
('AAG',    2, 376.4,  8,  0),
('AKK',    1, 227.2,  6,  0),
('AKK',    2, 337.2, 10,  0),
('AKL',    1, 370.9,  0,  0),
('AKL',    2, 386.1,  0,  0),
('CAH',    1, 222.4,  2,  0),
('CAH',    2, 244.6,  4,  0),
('CKK',    1, 237.0,  6,  0),
('CKK',    2, 269.7,  4,  0),
('CKL',    1, 264.2,  0,  0),
('CKL',    2, 290.6,  2,  0),
('FA',     1, 2.6,   10,  5),
('FA',     2, 2.9,   15,  5),
('FG',     1, 88.8,   4,  6),
('FG',     2, 97.7,   3,  6),
('GO25',   0, 3.0,   20, 10),
('GU25',   0, 3.0,   20, 10),
('GV25',   0, 1.1,   20, 10),
('K14',    0, 1.2,   40, 20),
('K14',    1, 1.2,   30, 20),
('K14',    2, 1.3,   15, 20),
('K16',    0, 1.9,   10, 15),
('K16',    1, 1.9,   15, 20),
('K16',    2, 2.1,   25, 20),
('K18',    1, 2.2,   22, 20),
('K18',    2, 2.4,   18, 20),
('K28',    0, 2.6,   12, 10),
('K28',    1, 2.6,    8, 10),
('K28',    2, 2.9,    6, 10),
('KR24',   0, 3.0,   18, 20),
('P18',    1, 2.3,   18, 20),
('P18',    2, 2.5,   22, 20),
('P24',    0, 2.9,   14, 20),
('P616',   0, 5.6,   16, 20),
('P624',   0, 6.2,   10, 15),
('PZ',     0, 2.4,   30, 20),
('RF',     0, 2.8,   30, 20),
('RK11',   1, 1.2,   30, 20),
('RK11',   2, 1.3,   30, 20),
('TL134',  1, 4.3,   15, 10),
('TL134',  2, 4.7,    8, 10),
('TR134',  1, 4.3,   15, 10),
('TR134',  2, 4.7,    8, 10);


CREATE TABLE Stueckliste(
Teil	    VARCHAR(8) NOT NULL,
TeilFarbe   INTEGER NOT NULL,
UTeil       VARCHAR(8) NOT NULL,
UTeilFarbe  INTEGER NOT NULL,
Anzahl      INTEGER NOT NULL,
PRIMARY KEY (Teil, TeilFarbe, UTeil, UTeilFarbe),
FOREIGN KEY (Teil, TeilFarbe) REFERENCES Teile(TeileID, Farbe),
FOREIGN KEY (UTeil, UTeilFarbe) REFERENCES Teile(TeileID, Farbe) );

INSERT INTO Stueckliste VALUES
('AAG', 1, 'CAH'  , 1 , 1  ),
('AAG', 1, 'K14'  , 1 , 9  ),
('AAG', 1, 'K16'  , 1 , 8  ),
('AAG', 1, 'P624' , 0 , 1  ),
('AAG', 1, 'TL134', 1 , 1  ),
('AAG', 1, 'TR134', 1 , 1  ),
('AAG', 2, 'CAH'  , 2 , 1  ),
('AAG', 2, 'K14'  , 2 , 9  ),
('AAG', 2, 'K16'  , 2 , 8  ),
('AAG', 2, 'P624' , 0 , 1  ),
('AAG', 2, 'TL134', 2 , 1  ),
('AAG', 2, 'TR134', 2 , 1  ),
('AKK', 1, 'CKK'  , 1 , 1  ),
('AKK', 1, 'K14'  , 1 , 6  ),
('AKK', 1, 'K16'  , 1 , 8  ),
('AKK', 2, 'CKK'  , 2 , 1  ),
('AKK', 2, 'K14'  , 2 , 6  ),
('AKK', 2, 'K16'  , 2 , 8  ),
('AKL', 1, 'CKL'  , 1 , 1  ),
('AKL', 1, 'K14'  , 1 , 2  ),
('AKL', 1, 'K16'  , 1 , 16 ),
('AKL', 2, 'CKL'  , 2 , 1  ),
('AKL', 2, 'K14'  , 2 , 2  ),
('AKL', 2, 'K16'  , 2 , 16 ),
('CAH', 1, 'FG'   , 1 , 1  ),
('CAH', 1, 'K18'  , 1 , 2  ),
('CAH', 1, 'P18'  , 1 , 2  ),
('CAH', 1, 'P624' , 0 , 1  ),
('CAH', 1, 'PZ'   , 0 , 1  ),
('CAH', 1, 'RK11' , 1 , 4  ),
('CAH', 2, 'FG'   , 2 , 1  ),
('CAH', 2, 'K18'  , 2 , 2  ),
('CAH', 2, 'P18'  , 2 , 2  ),
('CAH', 2, 'P624' , 0 , 1  ),
('CAH', 2, 'PZ'   , 0 , 1  ),
('CAH', 2, 'RK11' , 2 , 4  ),
('CKK', 1, 'FG'   , 1 , 1  ),
('CKK', 1, 'GO25' , 0 , 2  ),
('CKK', 1, 'GU25' , 0 , 2  ),
('CKK', 1, 'GV25' , 0 , 2  ),
('CKK', 1, 'K16'  , 0 , 1  ),
('CKK', 1, 'P616' , 0 , 2  ),
('CKK', 1, 'PZ'   , 0 , 1  ),
('CKK', 2, 'FG'   , 2 , 1  ),
('CKK', 2, 'GO25' , 0 , 2  ),
('CKK', 2, 'GU25' , 0 , 2  ),
('CKK', 2, 'GV25' , 0 , 2  ),
('CKK', 2, 'K16'  , 0 , 1  ),
('CKK', 2, 'P616' , 0 , 2  ),
('CKK', 2, 'PZ'   , 0 , 1  ),
('CKL', 1, 'FG'   , 1 , 1  ),
('CKL', 1, 'GO25' , 0 , 2  ),
('CKL', 1, 'GU25' , 0 , 2  ),
('CKL', 1, 'GV25' , 0 , 2  ),
('CKL', 1, 'K16'  , 0 , 1  ),
('CKL', 1, 'K18'  , 1 , 2  ),
('CKL', 1, 'P18'  , 1 , 2  ),
('CKL', 1, 'P624' , 0 , 2  ),
('CKL', 1, 'PZ'   , 0 , 1  ),
('CKL', 1, 'RK11' , 1 , 4  ),
('CKL', 2, 'FG'   , 2 , 1  ),
('CKL', 2, 'GO25' , 0 , 2  ),
('CKL', 2, 'GU25' , 0 , 2  ),
('CKL', 2, 'GV25' , 0 , 2  ),
('CKL', 2, 'K16'  , 0 , 1  ),
('CKL', 2, 'K16'  , 2 , 4  ),
('CKL', 2, 'P624' , 0 , 2  ),
('CKL', 2, 'PZ'   , 0 , 1  ),
('CKL', 2, 'RK11' , 2 , 4  ),
('FG' , 1, 'FA'   , 1 , 4  ),
('FG' , 1, 'K28'  , 0 , 2  ),
('FG' , 1, 'KR24' , 0 , 2  ),
('FG' , 1, 'P24'  , 0 , 4  ),
('FG' , 1, 'RF'   , 0 , 4  ),
('FG' , 2, 'FA'   , 2 , 4  ),
('FG' , 2, 'K28'  , 0 , 2  ),
('FG' , 2, 'KR24' , 0 , 2  ),
('FG' , 2, 'P24'  , 0 , 4  ),
('FG' , 2, 'RF'   , 0 , 4  );


CREATE TABLE Lieferanten(
  LiefNr     INTEGER NOT NULL,
  LiefName   VARCHAR(30) NOT NULL,
  LiefStadt  VARCHAR(30) NOT NULL,
  Bewertung  INTEGER CHECK (Bewertung IS NULL OR Bewertung BETWEEN -2 AND 2),
  PRIMARY KEY (LiefNr) );

INSERT INTO Lieferanten VALUES
  (000, '(intern)',	       '',           	 2),
  (500, 'Märzen AG',           'Ulm',       -1),
  (503, 'Fischer GmbH',        'München',    0),
  (506, 'Kalbe & Söhne',       'Augsburg',   2),
  (522, 'Faulstich AG',        'Weinheim',   2),
  (527, 'Meier KG',            'Ulm',        1),
  (528, 'Schröter & Co.',      'Singen',     2),
  (540, 'Koch GmbH & Co.KG',   'Senden',    -2),
  (545, 'Damm Ltd.',           'Hamburg',    1),
  (556, 'Schwarz KG',          'Ulm',        2),
  (557, 'Otto GmbH',           'Köln',       1),
  (558, 'Mayer GmbH & Co.KG.', 'Stuttgart', -2),
  (559, 'Franck & Sohn',       'Konstanz',   0),
  (563, 'Hennig KG',           'Heilbronn',  1),
  (572, 'Aberer',              'Stuttgart',  2),
  (575, 'Zander',              'Ulm',        2),
  (584, 'Decker',              'München',    1),
  (644, 'Lammert',             'Augsburg',   2);


CREATE TABLE Liefert(
  LiefNr  INTEGER NOT NULL,
  TeileID VARCHAR(8) NOT NULL,
  Farbe   INTEGER NOT NULL,
  Preis   DECIMAL(6,2),
  PRIMARY KEY (LiefNr, TeileID, Farbe),
  FOREIGN KEY (LiefNr) REFERENCES Lieferanten(LiefNr),
  FOREIGN KEY (TeileID, Farbe) REFERENCES Teile(TeileID, Farbe));

INSERT INTO Liefert VALUES
  (000, 'AAG',    1, 342.2),
  (000, 'AAG',    2, 342.2),
  (000, 'AKK',    1, 337.2),
  (000, 'AKK',    2, 337.2),
  (000, 'AKL',    1, 386.1),
  (000, 'AKL',    2, 386.1),
  (000, 'CAH',    1, 222.4),
  (000, 'CAH',    2, 222.4),
  (000, 'CKK',    1, 237.0),
  (000, 'CKK',    2, 237.0),
  (000, 'CKL',    1, 264.2),
  (000, 'CKL',    2, 264.2),
  (000, 'FG',     1,  88.8),
  (000, 'FG',     2,  88.8),
  (500, 'FA',     1,   2.5),
  (500, 'FA',     2,   2.5),
  (503, 'GO25',   0,   3.0),
  (503, 'GU25',   0,   3.0),
  (503, 'GV25',   0,   1.0),
  (506, 'K14',    0,   1.2),
  (506, 'K14',    1,   1.2),
  (506, 'K16',    0,   1.8),
  (506, 'K16',    1,   1.8),
  (506, 'K18',    1,   2.2),
  (506, 'K28',    0,   2.6),
  (506, 'K28',    1,   2.7),
  (506, 'K28',    2,   2.7),
  (522, 'K14',    0,   1.2),
  (522, 'K14',    1,   1.2),
  (522, 'K14',    2,   1.3),
  (522, 'K16',    0,   1.7),
  (522, 'K16',    1,   1.8),
  (522, 'K16',    2,   1.8),
  (522, 'K18',    1,   2.2),
  (522, 'K18',    2,   2.2),
  (527, 'K28',    0,   2.4),
  (527, 'K28',    1,   2.5),
  (527, 'K28',    2,   2.5),
  (540, 'KR24',   0,   3.0),
  (545, 'P18',    1,   2.2),
  (545, 'P18',    2,   2.2),
  (545, 'P24',    0,   2.9),
  (545, 'TL134',  1,   4.3),
  (545, 'TL134',  2,   4.2),
  (545, 'TR134',  1,   4.3),
  (545, 'TR134',  2,   4.2),
  (556, 'P616',   0,   5.5),
  (556, 'P624',   0,   6.2),
  (557, 'PZ',     0,   2.4),
  (559, 'RF',     0,   2.7),
  (563, 'RK11',   1,   1.2),
  (563, 'RK11',   2,   1.2),
  (572, 'TL134',  1,   4.2),
  (572, 'TL134',  2,   4.4),
  (572, 'TR134',  1,   4.2),
  (572, 'TR134',  2,   4.4),
  (575, 'FA',     1,   2.6),
  (575, 'FA',     2,   2.7),
  (584, 'RF',     0,   2.6),
  (644, 'GO25',   0,   2.8),
  (644, 'GU25',   0,   2.8),
  (644, 'GV25',   0,   0.9);


CREATE TABLE Bestellungen(
  BestNr    INTEGER NOT NULL,
  LiefNr    INTEGER NOT NULL,
  BestDatum DATE NOT NULL,
  PRIMARY KEY (BestNr),
  FOREIGN KEY (LiefNr) REFERENCES Lieferanten(LiefNr) );


CREATE TABLE BestellPos(
  BestNr    INTEGER NOT NULL,
  BestPos   INTEGER NOT NULL,
  TeileID   VARCHAR(8) NOT NULL,
  Farbe     INTEGER NOT NULL,
  Anzahl    INTEGER NOT NULL,
  PRIMARY KEY (BestNr, BestPos),
  FOREIGN KEY (BestNr) REFERENCES Bestellungen(BestNr),
  FOREIGN KEY (TeileID, Farbe) REFERENCES Teile(TeileID, Farbe));


INSERT INTO Bestellungen VALUES
  (103, 527, '2013-02-10');

INSERT INTO BestellPos VALUES
  (103, 1, 'K28', 1, 50),
  (103, 2, 'K28', 2, 30);


INSERT INTO Bestellungen VALUES
  (104, 500, '2013-02-11');

INSERT INTO BestellPos VALUES
  (104, 1, 'FA', 1, 4),
  (104, 2, 'FA', 2, 2);


INSERT INTO Bestellungen VALUES
  (105, 572, '2013-02-15');

INSERT INTO BestellPos VALUES
  (105, 1, 'TL134', 1, 6),
  (105, 2, 'TR134', 1, 6),
  (105, 3, 'TL134', 2, 2),
  (105, 4, 'TR134', 2, 2);


INSERT INTO Bestellungen VALUES
  (106, 522, '2013-02-22');

INSERT INTO BestellPos VALUES
  (106, 1, 'K14', 0, 10),
  (106, 2, 'K14', 2, 10),
  (106, 3, 'K16', 2, 20),
  (106, 4, 'K18', 2, 15),
  (106, 5, 'K18', 1, 20);


INSERT INTO Bestellungen VALUES
  (107, 584, '2013-02-23');

INSERT INTO BestellPos VALUES
  (107, 1, 'RF', 0, 8);


INSERT INTO Bestellungen VALUES
  (108, 545, '2013-02-23');

INSERT INTO BestellPos VALUES
  (108, 1, 'P18',    1, 20),
  (108, 2, 'P24',    0, 15),
  (108, 3, 'TL134',  1, 10),
  (108, 5, 'TL134',  2, 8),
  (108, 6, 'TR134',  2, 8);


INSERT INTO Bestellungen VALUES
  (109, 559, '2013-03-16');

INSERT INTO BestellPos VALUES
  (109, 1, 'RF', 0, 40);


INSERT INTO Bestellungen VALUES
  (110, 584, '2013-02-23');

INSERT INTO BestellPos VALUES
  (110, 1, 'RF', 0, 6);


INSERT INTO Bestellungen VALUES
  (111, 503, '2013-03-24');

INSERT INTO BestellPos VALUES
  (111, 1, 'GO25', 0, 10),
  (111, 2, 'GU25', 0, 10),
  (111, 3, 'GV25', 0, 10);


INSERT INTO Bestellungen VALUES
  (112, 522, '2013-04-15');

INSERT INTO BestellPos VALUES
  (112, 1, 'K14', 0, 20),
  (112, 2, 'K14', 1, 10),
  (112, 3, 'K16', 1, 15);


INSERT INTO Bestellungen VALUES
  (114, 500, '2013-02-11');

INSERT INTO BestellPos VALUES
  (114, 1, 'FA', 1, 4),
  (114, 2, 'FA', 2, 2);


INSERT INTO Bestellungen VALUES
  (115, 584, '2013-02-23');

INSERT INTO BestellPos VALUES
  (115, 1, 'RF', 0, 10);


CREATE TABLE Kunden(
  KdNr     INTEGER NOT NULL,
  KdName   VARCHAR(30) NOT NULL,
  KdStadt  VARCHAR(30) NOT NULL,
  Bonitaet INTEGER CHECK (Bonitaet IS NULL OR Bonitaet BETWEEN -2 and 2),
  PRIMARY KEY (KdNr) );

INSERT INTO Kunden VALUES
  (100, 'Walter',              'Siegen',     0),
  (105, 'Aberer',              'Stuttgart', -1),
  (112, 'Zander',              'Ulm',        2),
  (123, 'Babel',               'Senden',     1),
  (126, 'Moehne',              'Hamburg',    2),
  (136, 'Zimmer',              'Ulm',       -1),
  (137, 'Becker',              'Stuttgart',  0),
  (142, 'Schmidt',             'München',    1),
  (149, 'Womser',              'Trier',     -1),
  (150, 'Dehling',             'Augsburg',   2),
  (161, 'Kahlert',             'Stuttgart' , 1),
  (164, 'Lummer',              'Bonn',      -2),
  (177, 'Zwack',               'Senden',     2),
  (182, 'Manger',              'Bochum',     1),
  (184, 'Merz',                'Ulm',        2),
  (185, 'Ketzer',              'Bremen',     0),
  (188, 'Breu',                'Karlsruhe',  0),
  (192, 'Dreher',              'Ulm',       -1),
  (204, 'Fischer GmbH',        'München',    1),
  (219, 'Beyer AG',            'Bretten',    2),
  (222, 'Voigt & Co.',         'Flensburg', -1),
  (237, 'Graf KG',             'Ulm',        1),
  (251, 'Herrmann KG',         'Schwerin',  -1),
  (260, 'Koch GmbH & Co.KG',   'Senden',     1),
  (263, 'Fobke GmbH',          'Bremen',     2);


CREATE TABLE Preisliste (
  TeileID VARCHAR(8) NOT NULL,
  Farbe   INTEGER NOT NULL,
  Preis   DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (TeileID, Farbe),
  FOREIGN KEY (TeileID, Farbe) REFERENCES Teile(TeileID, Farbe));

INSERT INTO Preisliste (TeileID, Farbe, Preis)
  (SELECT t.TeileID, t.Farbe, t.KalkKosten * 1.4 
   FROM   Teile AS t
   WHERE  TeileID IN ('AAG', 'AKK', 'AKL', 'CAH', 'CKK', 'CKL', 'FG'));


CREATE TABLE Auftraege(
  AuftrNr         INTEGER NOT NULL,
  KdNr            INTEGER NOT NULL, 
  AuftrDatum      DATE NOT NULL,
  PRIMARY KEY (AuftrNr),
  FOREIGN KEY (KdNr) REFERENCES Kunden(KdNr) );


CREATE TABLE AuftragsPos(
  AuftrNr  INTEGER NOT NULL,
  Pos      INTEGER NOT NULL,
  TeileID  VARCHAR(8) NOT NULL,
  Farbe    INTEGER NOT NULL,
  Anzahl   INTEGER NOT NULL,
  PRIMARY KEY (AuftrNr, Pos),
  FOREIGN KEY (AuftrNr) REFERENCES Auftraege(AuftrNr),
  FOREIGN KEY (TeileID, Farbe) REFERENCES Teile(TeileID, Farbe) );


INSERT INTO Auftraege VALUES
(15, 177, '2013-02-23');

INSERT INTO AuftragsPos VALUES
(15, 1, 'AAG', 1, 5),
(15, 2, 'AAG', 2, 2),
(15, 3, 'CAH', 1, 1);


INSERT INTO Auftraege VALUES
(16, 123, '2013-02-25');

INSERT INTO AuftragsPos VALUES
(16, 1, 'CKK', 1, 2),
(16, 2, 'FG', 1, 4);


INSERT INTO Auftraege VALUES
(18, 126, '2013-03-10');

INSERT INTO AuftragsPos VALUES
(18, 1, 'CKK', 1, 6),
(18, 2, 'CKK', 2, 2),
(18, 3, 'FG',  1, 5),
(18, 4, 'AKL', 2, 1);


INSERT INTO Auftraege VALUES
(19, 123, '2013-03-10');

INSERT INTO AuftragsPos VALUES
(19, 1, 'AAG', 2, 1);


INSERT INTO Auftraege VALUES
(29, 150, '2013-03-10');

INSERT INTO AuftragsPos VALUES
(29, 1, 'CKL', 2, 2),
(29, 2, 'CAH', 1, 1);


INSERT INTO Auftraege VALUES
(21, 123, '2013-03-11');

INSERT INTO AuftragsPos VALUES
(21, 1, 'AKK', 1, 4),
(21, 2, 'AKK', 2, 4);


INSERT INTO Auftraege VALUES
(22, 251, '2013-03-11');

INSERT INTO AuftragsPos VALUES
(22, 1, 'CKL', 2, 1),
(22, 2, 'AAG', 1, 1);


INSERT INTO Auftraege VALUES
(24, 219, '2013-03-13');

INSERT INTO AuftragsPos VALUES
(24, 1, 'CAH', 2, 2),
(24, 2, 'FG', 2, 4),
(24, 3, 'AKK', 1, 2);


INSERT INTO Auftraege VALUES
(26, 150, '2013-03-15');

INSERT INTO AuftragsPos VALUES
(26, 1, 'AAG', 2, 10);


INSERT INTO Auftraege VALUES
(27, 123, '2013-03-15');

INSERT INTO AuftragsPos VALUES
(27, 1, 'AKL', 2, 5);


CREATE TABLE Abteilungen (
  AbtNr     CHAR(2) NOT NULL,
  AbtName   VARCHAR(30) NOT NULL UNIQUE,
  MgrPersNr INTEGER ,
  PRIMARY KEY (AbtNr) );

INSERT INTO Abteilungen VALUES
  ('BH', 'Buchhaltung', NULL),
  ('EK', 'Einkauf', NULL),
  ('FE', 'Forschung und Entwicklung', NULL),
  ('FP', 'Fuhrpark', NULL),
  ('FT', 'Fertigung', NULL),
  ('GF', 'Geschäftsführung', NULL),
  ('LG', 'Lager', NULL),
  ('PA', 'Personal', NULL),
  ('PL', 'Planung', NULL),
  ('RA', 'Rechtsabteilung', NULL),
  ('VK', 'Verkauf', NULL);


CREATE TABLE Mitarbeiter (
  PersNr     INTEGER NOT NULL,
  Name       VARCHAR(30) NOT NULL,
  Vorname    VARCHAR(30) NOT NULL,
  Wohnort    VARCHAR(30) NOT NULL,
  Funktion   VARCHAR(30) NOT NULL,
  Gehalt     DECIMAL(8,2),
  AbtNr      CHAR(2),
  ChefPersNr INTEGER,
  PRIMARY KEY (PersNr),
  FOREIGN KEY (AbtNr) REFERENCES Abteilungen(AbtNr),
  FOREIGN KEY (ChefPersNr) REFERENCES Mitarbeiter(PersNr));

ALTER TABLE Abteilungen ADD CONSTRAINT MgrPersNr 
  FOREIGN KEY (MgrPersNr) REFERENCES Mitarbeiter(PersNr);


/* Erst die Leiter einfügen, das sonst Probleme wg. referentieller Integrität */

INSERT INTO Mitarbeiter VALUES
  (1344, 'Maier',     'Ilse',    'Ulm',        'Geschäftsführer',        7500, 'GF', NULL),
  (1101, 'Schmidt',   'Karl',    'Senden',     'Leiter Lager',           3200, 'LG', 1344),
  (1387, 'Weck',      'Helga',   'Ulm',        'Leiter Personal',        4800, 'PA', 1344),
  (1391, 'Heller',    'Martin',  'Neu-Ulm',    'Leiter Fertigung',       4300, 'FT', 1344),
  (1411, 'Zemmler',   'Max',     'Blaustein',  'Leiter Planung',         4100, 'PL', 1344),
  (1412, 'Dr. Zack',  'Ulrich',  'Blaubeuren', 'Leiter Rechtsabteilung', 4500, 'RA', 1344),
  (1495, 'Müller',    'Tobias',  'Erbach',     'Leiter Fuhrpark',        3300, 'FP', 1344),
  (1649, 'Zapf',      'Jens',    'Neu-Ulm',    'Leiter Verkauf',         7100, 'VK', 1344),
  (1663, 'Dr. Blick', 'Hans',    'Ulm',        'Leiter F & E',           5200, 'FE', 1344),
  (1701, 'Beck',      'Theresa', 'Pfuhl',      'Leiter Buchhaltung',     1300, 'BH', 1344),
  (1730, 'Wanner',    'Peter',   'Ulm',        'Leiter Einkauf',         4200, 'EK', 1344);


INSERT INTO Mitarbeiter VALUES
  (1198, 'Becker',    'Susanne', 'Biberach',   'Debitoren',              2500, 'BH', 1701),
  (1199, 'Moser',     'Hartmut', 'Biberach',   'Finanzplanung',          3200, 'BH', 1701),
  (1220, 'Schmalz',   'Bernd',   'Ulm',        'Auftragsabwicklung',     2300, 'VK', 1649),
  (1287, 'Wauke',     'Magda',   'Neu-Ulm',    'Lohn & Gehalt',          2300, 'BH', 1701),
  (1300, 'Fliege',    'Hilde',   'Neu-Ulm',    'Personalplanung',        3200, 'PA', 1387),
  (1309, 'Unger',     'Martin',  'Senden',     'Fahrer',                 1900, 'FP', 1495),
  (1332, 'Maier',     'Achim',   'Ulm',        'Transportplanung',       2500, 'FP', 1495),
  (1373, 'Kramer',    'Inge',    'Ulm',        'Finanzanlagen',          3700, 'BH', 1701),
  (1430, 'Wecker',    'Lutz',    'Erbach',     'Kreditoren',             2300, 'BH', 1701),
  (1444, 'Deiters',   'Uwe',     'Senden',     'Lager',                  1650, 'LG', 1101),
  (1452, 'Bauer',     'Isolde',  'Senden',     'Mahnwesen',              2200, 'BH', 1701),
  (1466, 'Brunner',   'Bernd',   'Neu-Ulm',    'Fahrer',                 1900, 'FP', 1495),
  (1522, 'Neubert',   'Irene',   'Ulm',        'Montage II',             1800, 'FT', 1391),
  (1581, 'Mayer',     'Jürgen',  'Neu-Ulm',    'Rechnungsprüfung',       2600, 'BH', 1701),
  (1588, 'Buck',      'Hans',    'Pfuhl',      'Sonderteile',            2800, 'EK', 1730),
  (1591, 'Zenker',    'Beate',   'Ulm',        'Normteile',              2500, 'EK', 1730),
  (1592, 'Meyer',     'Peter',   'Erbach',     'Verpackung',             2700, 'EK', 1730),
  (1599, 'Müller',    'Ludwig',  'Neu-Ulm',    'Montage II',             1700, 'FT', 1391),
  (1611, 'Amman',     'Thea',    'Neu-Ulm',    'Kasse',                  2300, 'BH', 1701),
  (1612, 'Cramer',    'Pia',     'Ulm',        'Montage II',             1850, 'FT', 1391),
  (1617, 'Mack',      'Petra',   'Ulm',        'Montage I',              1800, 'FT', 1391),
  (1620, 'Grupp',     'Luise',   'Ulm',        'Sekretariat',            1800, 'BH', 1701),
  (1622, 'Müller',    'Heinz',   'Weingarten', 'Rechnungsprüfung',       2700, 'BH', 1701),
  (1681, 'Binder',    'Jakob',   'Erbach',     'Montage I',              1700, 'FT', 1391),
  (1720, 'Wammer',    'Ruth',    'Blaustein',  'Personalverwaltung',     2600, 'PA', 1387),
  (1733, 'Becker',    'Max',     'Ulm',        'Anlagen',                2200, 'BH', 1701),
  (1770, 'Pfleiderer','Ilse',    'Ulm',        'Sekretariat',            2500, 'GF', 1344),
  (1776, 'Ohlig',     'Paula',   'Ulm',        'Produktplanung',         3100, 'PL', 1411),
  (1782, 'Opitz',     'Karla',   'Ulm',        'Montage II',             1780, 'FT', 1391),
  (1788, 'Wacker',    'Petra',   'Erbach',     'F & E',                  3200, 'FE', 1663),
  (1829, 'Trenker',   'Paula',   'Ulm',        'Sekretariat',            2200, 'GF', 1344),
  (1877, 'Müller',    'Heinrich','Wernau',     'Lager',                  1700, 'LG', 1101),
  (1889, 'Lauer',     'Frida',   'Ehningen',   'Montage I',              1750, 'FT', 1391),
  (1891, 'Aberer',    'Karl',    'Ulm',        'F & E',                  2800, 'FE', 1663),
  (1911, 'Lummer',    'Hans',    'Blaustein',  'Produktplanung',         2900, 'PL', 1411),
  (1922, 'Zander',    'Philip',  'Einsingen',  'Lager',                  1800, 'LG', 1101),
  (1929, 'Decker',    'Paul',    'Senden',     'Auftragsdisposition',    2600, 'VK', 1649),
  (1974, 'Frommert',  'Margit',  'Ulm',        'Auftragserfassung',      2200, 'VK', 1649),
  (1998, 'Gerke',     'Sonja',   'Neu-Ulm',    'Auftragserfassung',      2200, 'VK', 1649),
  (2001, 'Moser',     'Luise',   'Neu-Ulm',    'Sekretariat',            2300, 'RA', 1412),
  (2645, 'Kratzer',   'Lothar',  'Einsingen',  'Auftragsabwicklung',     2600, 'VK', 1649),
  (2717, 'Pauler',    'Thomas',  'Ulm',        'Teiledisposition',       2400, 'VK', 1649);


UPDATE Abteilungen SET MgrPersNr = 1701 WHERE AbtNr = 'BH';
UPDATE Abteilungen SET MgrPersNr = 1730 WHERE AbtNr = 'EK';
UPDATE Abteilungen SET MgrPersNr = 1663 WHERE AbtNr = 'FE';
UPDATE Abteilungen SET MgrPersNr = 1495 WHERE AbtNr = 'FP';
UPDATE Abteilungen SET MgrPersNr = 1391 WHERE AbtNr = 'FT';
UPDATE Abteilungen SET MgrPersNr = 1344 WHERE AbtNr = 'GF';
UPDATE Abteilungen SET MgrPersNr = 1101 WHERE AbtNr = 'LG';
UPDATE Abteilungen SET MgrPersNr = 1387 WHERE AbtNr = 'PA';
UPDATE Abteilungen SET MgrPersNr = 1411 WHERE AbtNr = 'PL';
UPDATE Abteilungen SET MgrPersNr = 1412 WHERE AbtNr = 'RA';
UPDATE Abteilungen SET MgrPersNr = 1649 WHERE AbtNr = 'VK';


CREATE TABLE Sonderpreise(
  TeileID VARCHAR(8) NOT NULL,
  Farbe   INTEGER NOT NULL,
  Preis   DECIMAL(8,2),
  PRIMARY KEY (TeileID, Farbe),
  FOREIGN KEY (TeileID, Farbe) REFERENCES Teile(TeileID, Farbe));

INSERT INTO Sonderpreise VALUES
  ('AAG', 1, 450),
  ('AAG', 2, 500),
  ('AKK', 1, NULL),
  ('AKK', 2, 450),
  ('AKL', 1, 500),
  ('AKL', 2, NULL),
  ('CAH', 1, 300),
  ('CAH', 2, 320),
  ('CKK', 1, NULL),
  ('CKK', 2, NULL),
  ('CKL', 1, 350),
  ('CKL', 2, 390),
  ('FG', 1, NULL),
  ('FG', 2, NULL); 
