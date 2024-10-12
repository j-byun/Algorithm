-- 코드를 작성해주세요
SELECT A.ID, B.FISH_NAME, A.LENGTH
FROM FISH_INFO A
JOIN FISH_NAME_INFO B
ON A.FISH_TYPE = B.FISH_TYPE
JOIN (SELECT FISH_TYPE, MAX(LENGTH) AS MAX_LENGTH
      FROM FISH_INFO
      GROUP BY FISH_TYPE) C
ON A.FISH_TYPE = C.FISH_TYPE
WHERE A.LENGTH = C.MAX_LENGTH
ORDER BY 1