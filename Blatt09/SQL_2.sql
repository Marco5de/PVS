/* Aufgabe 2a) */
SELECT 
	ab.*, l.Name, l.Wohnort 
FROM 
	Abteilungen AS ab 
	JOIN 
		Mitarbeiter as l 
	ON 
		ab.MgrPersNr = l.PersNr

/* Aufgabe 2b) */
SELECT DISTINCT
	t.*, lief.LiefName, lief.Bewertung
FROM 
	Teile AS t
    JOIN
    	Liefert AS l
    ON t.teileID = l.TeileID
    JOIN
    	Lieferanten AS lief
    ON lief.liefNr = l.liefNr
WHERE
	lief.LiefStadt = 'Ulm' AND lief.Bewertung > 0

/* Aufgabe 2c) */
SELECT 
	auf.*
FROM
	AuftragsPos AS auf
    JOIN
    	Auftraege AS bez
    ON auf.AuftrNr = bez.AuftrNr
    JOIN
		Kunden AS k
	ON k.KdNr = bez.KdNr
WHERE
	k.KdName = 'Dehling'

/* Aufgabe 2d) */
SELECT 
	lief.*, COUNT(*) AS AnzahlBestellungen
FROM
	Lieferanten AS lief
    JOIN 
   		Bestellungen AS b
    ON b.LiefNr = lief.LiefNr
GROUP BY 
	lief.LiefNr

/* Aufgabe 2e) */
SELECT 
	AuftragZählung.KdNr, AuftragZählung.KdName, AuftragZählung.KdStadt, AuftragZählung.Bonitaet
FROM (
	SELECT
		kd.*, COUNT(*) AS AnzahlBestellungen
	FROM
		Kunden AS kd
	    JOIN
 		   	Auftraege AS a
 		ON kd.KdNr = a.KdNR
	GROUP BY
		kd.KdNr
) AS AuftragZählung
WHERE
	AnzahlBestellungen > 1

/* Aufgabe 2f) */
SELECT
	AVG(m.Gehalt)
FROM
	Mitarbeiter AS m
    JOIN
    	Abteilungen AS ab
    ON m.AbtNr = ab.AbtNr
WHERE
	ab.AbtName = 'Lager'

/* Aufgabe 2g) */
SELECT 
	m.*
FROM
	Mitarbeiter AS m
WHERE
	m.Gehalt = (
    SELECT 
        MAX(Gehalt) 
    FROM 
        Mitarbeiter AS m
    WHERE
		m.PersNr NOT IN(
       		SELECT m2.PersNr
        	FROM Mitarbeiter AS m2
        	JOIN Abteilungen AS ab
        	ON ab.MgrPersNr = m2.PersNr
    	)
    )

/* Aufgabe 2h) */
SELECT 
	a.AuftrNr, SUM(t.KalkKosten) AS Betrag
FROM
	AuftragsPos AS a
    JOIN
    	Teile AS t
    ON
    	t.TeileID = a.TeileId
GROUP BY
	a.AuftrNr

/* Aufgabe 2i) */
SELECT 
	a.*, COUNT(ap.AuftrNr) AS AnzahlAuftragPos
FROM
	Auftraege AS a
    JOIN
    	AuftragsPos AS ap
    ON
    	ap.AuftrNr = a.AuftrNr
GROUP BY
	a.AuftrNr

/* Aufgabe 2j) */
SELECT 
	k.KdName AS Erster_Kunde,
    k2.KdName AS Zweiter_Kunde,
    k2.KdStadt
FROM
	Kunden AS k
    CROSS JOIN
    	Kunden AS k2
WHERE
	k.KdName > k2.KdName
AND
    k.KdStadt = k2.kdStadt  
ORDER BY 
	k2.KdStadt ASC

/* Aufgabe 2k) */
SELECT DISTINCT
	l.LiefNr, t.TeileID, 
    f.FarbeText AS Farbe
    t.KalkKosten, li.Preis, 
    (t.KalkKosten - li.Preis) AS Ersparnis
FROM
	Teile AS t
	JOIN
    	Farbcodes AS f
    ON t.Farbe = f.FarbCode
    JOIN
    	Liefert AS li
    ON
    	li.TeileID = t.teileID
    JOIN
    	Lieferanten AS l
    ON
    	li.LiefNr = l.LiefNr
WHERE
	(t.KalkKosten - li.Preis) > 0

/* Aufgabe 2l) (mit Left Outer Join) */
SELECT
	a.LiefName
FROM (
	SELECT 
		k.*, l.LiefName
	FROM
		Lieferanten AS l
 		LEFT OUTER JOIN
 	   		Kunden AS k
    	ON k.KdName = l.LiefName
) AS a
WHERE
	a.KdName IS NULL

/* Aufgabe 2l) (mit korrelierter Sub-Query) */
SELECT
	l.LiefName
FROM
	Lieferanten AS l
WHERE
	NOT EXISTS(
        SELECT
			k.KdName
        FROM 
			Kunden AS k
        WHERE
        k.KdName = l.LiefName
    )

/* Aufgabe 2m) (mit korrelierter Sub-Query) */
SELECT
	t.TeileID,
    f.FarbeText AS Farbe
FROM
	Teile AS t
    JOIN
    	Farbcodes AS f
    ON t.Farbe = f.FarbCode
WHERE NOT EXISTS(
    SELECT 
    	a.TeileID, a.Farbe
    FROM 
    	AuftragsPos AS a
	WHERE 
    	t.TeileID = a.TeileID
		AND a.Farbe = t.Farbe
)

/* Aufgabe 2m) (mit unkorrelierter Sub-Query) */
SELECT
	t.TeileID,
    f.FarbeText AS Farbe
FROM
	Teile AS t
    JOIN
    	Farbcodes AS f
    ON t.Farbe = f.FarbCode
WHERE 
	(t.TeileID, t.Farbe) NOT IN(
    SELECT 
    	a.TeileID, a.Farbe
    FROM 
    	AuftragsPos AS a
    )
