
SELECT person_name as name, person_email as email, person_phone_number as phone_number FROM MONITORED_INDIVIDUAL
WHERE record_id IN (
SELECT DISTINCT id_1 AS id FROM (
(SELECT person_2 AS id_1 FROM contact WHERE person_1 = 
(SELECT record_id FROM MONITORED_INDIVIDUAL WHERE person_email = 'dub.vizer@br.com')
)
UNION
(SELECT person_1 AS id_2 FROM contact WHERE person_2 = 
(SELECT record_id FROM MONITORED_INDIVIDUAL WHERE person_email = 'dub.vizer@br.com'))))
