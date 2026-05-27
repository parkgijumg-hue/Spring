-- 사용자별 구매이력(구매 없는 사용자는 제외), 모든 컬럼 출력
# SELECT u.*, b.*
# FROM usertbl u
#          INNER JOIN buytbl b
#                     ON u.userID = b.userID


-- userID가 'JYP'인 데이터만 출력하세요.
# SELECT u.*, b.*
# FROM usertbl u
#          INNER JOIN buytbl b
#                     ON u.userID = b.userID
# WHERE u.userID = 'JYP';

-- 구매이력이 없는 사용자도 출력
# SELECT
#     u.userID,
#     u.name,
#     b.prodName,
#     u.addr,
#     u.mobile1
# FROM usertbl u
#          LEFT JOIN buytbl b
#                    ON u.userID = b.userID
# ORDER BY u.userID ASC;

-- 1) sqlDB의 사용자를 모두 조회하되 전화가 없는 사람은 제외(= 전화 있는 사람만)
SELECT *
FROM usertbl
WHERE mobile1 IS NOT NULL;      -- (또는 mobile1 <> '' 조건 추가 가능)

-- 2) sqlDB의 사용자를 모두 조회하되 전화가 없는 사람만 출력
SELECT *
FROM usertbl
WHERE mobile1 IS NULL;