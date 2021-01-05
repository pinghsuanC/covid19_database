SELECT professional_name AS staff_name, ob_timestamp AS time, ob_text AS full_text FROM 

(OBSERVATION 
JOIN
HEALTH_PROFESSIONAL
ON Observation.hospital_id = Health_Professional.hospital_id)

WHERE OB_TEXT LIKE '%breathing%'
AND record_id = (SELECT record_id FROM MONITORED_INDIVIDUAL WHERE person_name = 'Samantha Adam')

ORDER BY time DESC;
