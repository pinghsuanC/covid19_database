SELECT drugName_A AS DRUGNAME, RECOVERED, UNRECOVERED FROM (
(SELECT COUNT(record_id) AS RECOVERED, drugName_A FROM (
SELECT record_id, drug_name AS drugName_A, ad_id FROM ADMINISTRATION WHERE record_id IN
(SELECT record_id FROM CONFIRMED_PATIENT WHERE status = 'recovered')
) GROUP BY drugName_A)

JOIN

(SELECT COUNT(record_id) AS UNRECOVERED, drugName_B FROM (
SELECT record_id, drug_name As drugName_B, ad_id FROM ADMINISTRATION WHERE record_id IN
(SELECT record_id FROM CONFIRMED_PATIENT WHERE status = 'deceased' OR status = 'infected')
) GROUP BY drugName_B)

ON drugName_A = drugName_B

)