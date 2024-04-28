-- 코드를 입력하세요
SELECT m.MEMBER_NAME, r.REVIEW_TEXT, DATE_FORMAT(r.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM REST_REVIEW r
INNER JOIN MEMBER_PROFILE m
ON r.MEMBER_ID = m.MEMBER_ID
WHERE r.MEMBER_ID = (SELECT MEMBER_ID
                    FROM REST_REVIEW
                    GROUP BY MEMBER_ID
                    ORDER BY COUNT(*) DESC
                    LIMIT 1)
ORDER BY r.REVIEW_DATE, r.REVIEW_TEXT;

# SELECT * FROM REST_REVIEW ORDER BY MEMBER_ID;
# SELECT MEMBER_ID, COUNT(*)
#                     FROM REST_REVIEW
#                     GROUP BY MEMBER_ID
#                     ORDER BY COUNT(*) DESC
#                     # LIMIT 1;
