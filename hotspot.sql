SELECT b_name, COUNT(record_id) AS num_infected FROM (
SELECT b_name, record_id FROM MONITORED_INDIVIDUAL 
WHERE b_name IS NOT NULL
AND
record_id IN (
SELECT DISTINCT record_id FROM (
SELECT record_id FROM POTENTIAL_INDIVIDUAL WHERE test_result= 'positive'
UNION
SELECT record_id FROM CONFIRMED_PATIENT))
)

GROUP BY b_name
ORDER BY num_infected DESC, b_name
;
