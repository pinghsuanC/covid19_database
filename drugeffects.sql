

SELECT * FROM
(
SELECT MIN(ob_timestamp) AS time_dizz, record_id AS time_id FROM OBSERVATION
WHERE ob_text LIKE '%dizz%'
GROUP BY record_id
)
JOIN
(SELECT MIN(ad_timestamp) AS time_drug, record_id AS drug_id FROM ADMINISTRATION
GROUP BY record_id)
ON
time_dizz > time_drug AND time_id = drug_id;