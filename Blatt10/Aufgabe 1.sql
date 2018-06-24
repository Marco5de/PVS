/* Aufgabe 1a) */
INSERT INTO
	Lieferanten
VALUES
	(28, 'GmbH und KOLB AG', 'Ulm', 2)

/* Aufgabe 1b) */
INSERT INTO Liefert(
	SELECT
		LiefNr, 'TL134', Farbe, Preis*3
	FROM
		Liefert
	WHERE
		TeileID = 'K18'
    )

/* Aufgabe 1c)
 * Anmerkung: 1 Teil kann in verschiedenen Farben sein,
 * dies zählt aber immer noch als gleiches Teil 
 * (deswegen das DISTINCT - Schlüsselwort). */
DELETE FROM Liefert
WHERE 
	LiefNr IN(
SELECT
	l.LiefNr
FROM(
	SELECT DISTINCT
		LiefNr, TeileID
	FROM
		Liefert
)
AS l
GROUP BY
	l.LiefNr
HAVING
	COUNT(*) = 1
)

/* Aufgabe 1d) */
UPDATE
	Mitarbeiter
SET
	Gehalt = Gehalt * 0.8
WHERE
	Wohnort = 'Ulm'

/* Aufgabe 1e) */
UPDATE 
	AuftragsPos
SET Farbe = 1
WHERE (
    AuftragsPos.AuftrNr, AuftragsPos.Pos) IN(
SELECT *
FROM(
SELECT
	ap.AuftrNr, ap.Pos
FROM
	AuftragsPos AS ap
    JOIN
    	Auftraege AS a
    ON
    	a.AuftrNr = ap.AuftrNr
    JOIN
    	Kunden AS k
    ON
    	k.KdNr = a.KdNr
WHERE
	k.KdName = 'Babel'
AND
    ap.Farbe = 2
) AS ap3
)
