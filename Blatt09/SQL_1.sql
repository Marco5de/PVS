/* Aufgabe 1a) */
SELECT * 
FROM Mitarbeiter 
WHERE Wohnort <> 'München'

/* Aufgabe 1b) */
SELECT DISTINCT LiefNr
FROM Liefert

/* Aufgabe 1c) */
SELECT l.*
FROM Liefert AS l
WHERE l.Preis >= 300 AND l.Preis <= 430

/* Aufgabe 1d) */
SELECT *
FROM Bestellungen
WHERE BestDatum NOT BETWEEN '2009-02-15' AND '2009-02-23'

/* Aufgabe 1e) */
SELECT *
FROM Kunden
WHERE Kunden.KdStadt LIKE 'S%'

/* Aufgabe 1f) */
SELECT t.KalkKosten, t.TeileID, t.Farbe
FROM Teile As t

/* Aufgabe 1g) */
SELECT m.Gehalt
FROM Mitarbeiter AS m
WHERE m.Funktion = 'Montage II'
ORDER BY m.Gehalt ASC

/* Aufgabe 1h) */
SELECT a.*, 
	a.AuftrDatum + INTERVAL 3 MONTH AS Skontodaum 
FROM Auftraege AS a

/* Aufgabe 1i) */
SELECT a.AuftrNr, a.AuftrDatum 
FROM Auftraege AS a 
WHERE day(a.AuftrDatum)%2 = 0

/* Aufgabe 1j) */
SELECT t.*, 
	CASE 
		WHEN t.Bestand > t.MinBestand THEN 'Überbestand' 
		WHEN t.Bestand < t.MinBestand THEN 'Mangel'
		ELSE 'Mindestbestand' 
	END AS 'Bewertung' 
FROM Teile AS t
