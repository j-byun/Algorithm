SELECT SUM(C.CNT) AS FISH_COUNT
FROM (
    SELECT COUNT(*) AS CNT
        , I.FISH_TYPE
        , N.FISH_NAME
    FROM FISH_INFO I
    JOIN FISH_NAME_INFO N
    ON I.FISH_TYPE = N.FISH_TYPE
    GROUP BY I.FISH_TYPE, N.FISH_NAME    
    ) C
WHERE C.FISH_NAME = 'BASS' 
OR C.FISH_NAME = 'SNAPPER'