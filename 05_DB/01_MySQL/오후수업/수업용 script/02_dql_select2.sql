# Builtin 함수
# - 문자형/숫자형/날짜형 자료형별 함수 제공
# - 반드시 반환값을 가진다.
# https://dev.mysql.com/doc/refman/8.0/en/built-in-function-reference.html

# 문자형 함수
# instr(대상문자열, 검색문자열) -> 인덱스

    select
        instr('사과딸기바나나','딸기'), -- 3번째 문자열에서 검색문자열(딸기) 시작한다.
        instr('사과딸기바나나','키위') -- 0 (존재하지 않음)
        ;

    select *
    from tbl_menu
    where instr(menu_name,'마늘')>0 -- 메뉴명에 마늘이 포함된 메뉴 찾기
    ;

# format(숫자, 소수점자리수) -> 포맷팅문자열

    select format(1234567890.12345,3);

# lpad/rpad(문자열, 길이, 패딩문자)
# - 임의문자를 채워서 특정길이의 문자열 반환
select lpad('hello',10,'*') lp,
       rpad('hello',7,'*') rp
;

# substring(대상문자열, 시작인덱스, 길이) -> 잘라낸 문자열
select
    substring('hello world',7,3),      -- wor
    substring('hello world',7,11),     -- world
    substring('hello world',7),        -- world
    substring('hello world',-5),       -- world
    substr('hello world',-5)           -- world

;

# 정규표현식 (문자열 검색 특화 표현식)
# regexp_replace(문자열,패턴,바꿀값)
# regexp_instr(문자열,패턴)
# regexp_substr(문자열,패턴)
select
    regexp_replace('123,456원','[^0-9]',''), -- 123456
    regexp_instr('$123.456','[0-9]'), -- 2 ( [] 안에있는 조건에 만족하는 위치 반환)
    regexp_substr('제 키는 150cm 입니다.','[0-9]+') -- + : 한개 이상 반복을 의미함
                                                 -- [0-9] : 숫자

;


# 숫자처리함수
select
    ceiling(1234.56), -- 올림처리
    floor(1234.56), -- 내림처리
    truncate(1234.56,2), -- 절삭
    round(1234.56), -- 반올림
    round(1234.567,2) -- 2째자리에서 반올림
;

# 날짜처리함수
# adddate(date, 일수)
# subdate(date, 일수)
select
    now(),
    adddate(now(),1),
    subdate(now(),1),
    adddate(now(),interval 10 year ) -- interval : 시간 자료형 day/month/year
    ;

# extract(단위 from datetime) -> 숫자

select
    extract(year from now()), -- 년
    extract(month from now()), -- 월
    extract(day from now()), -- 일
    extract(hour from now()), -- 시
    extract(minute from now()), -- 분
    extract(second from now()) -- 초
;

# date_format(datetime, 형식문자열) -> 문자열

select
    date_format(now(),'%y/%m/%d'),
    date_format(now(),'%Y/%m/%d'),
    date_format(now(),'%h:%i')
;

# str_to_date(문자열, 형식문자열) -> datetime
select
    str_to_date('26/05/27','%y/%m/%d'),
    str_to_date('2026/05/27','%Y/%m/%d')
;

# 기타함수
# null처리 함수 - ifnull(값, null일때 값)

select
    ifnull(category_code,'미지정')
from tbl_menu;

# 삼항연산처리 - if(조건식, 참일때 값, 거짓일때 값)
-- isnull(값) : null이면 1, 아니면 0반 환
select
    isnull(category_code),
    if(isnull(category_code),'미지정',category_code) category_code
from tbl_menu;

-- 메뉴 가격이 만원 미만이면 싸다,아니면 비싸다
    select menu_name,menu_price,
           if(menu_price<10000,'싸다','비싸다') '가격 평가'

        from tbl_menu
    order by menu_price
    ;

-- =============================================================
# 그룹함수
# - 특정행을 그룹핑하고 그룹별로 하나의 결과를 반환하는 함수
# - group by 구문을 사용해 그룹핑, group by를 사용하지 않으면 전체가 하나의 그룹
# - 그룹핑과 관계없는 일반컬럼은 조회불가

# sum(컬럼)
# - null이 아닌 컬럼의 합

SELECT SUM(menu_price) AS total_menu_price -- 메뉴 가격 총합 구하기
FROM tbl_menu;

-- 카테고리 코드가 10번인 메뉴들의 가격 합계 조회
SELECT category_code, SUM(menu_price) AS total_price
FROM tbl_menu
GROUP BY category_code      -- where category_code=10; 이랑 같은 의미
HAVING category_code = 10;


# avg(컬럼) -> 평균값
# - null인 컬럼은 제외한 평균값

select truncate(avg(menu_price),0) from tbl_menu;

# count(컬럼) -> 개수
# - null인 컬럼은 제외하고 개수 집계

select count(*), -- * : 모든 컬럼 = 모든 행
       count(menu_price) -- count(컬럼명) : null인 컬럼 제외
from
    tbl_menu;

insert into tbl_menu values(22,'순대쉐이크',null,null,'Y');
commit;
#menu_price, category_code 제약조건 변경
alter table tbl_menu
modify menu_price  int null,
modify category_code int null;

-- select * from tbl_menu;


# 카테고리코드가 null이 아닌 행 개수 조회
select count(category_code) from tbl_menu;

select * from tbl_menu where category_code is not null;



# max/min
# - 숫자/문자열/날짜시간에 대해 최대/최소값을 반환
select
    max(menu_price),
    min(menu_price),
    max(menu_name),
    min(menu_name)
from tbl_menu;



-- =====================================================
# group by 구문
# - 특정컬럼을 기준으로 grouping을 수행
# - 그룹함수와 함께 사용
select
    category_code,
    count(*),
    avg(menu_price)
from tbl_menu
group by category_code; -- category_code값이 같은 행끼리 그룹핑 처리(null도 하나의 그룹으로 처리)

# 주문가능 여부에 따른 메뉴개수 집계
select
     orderable_status,
     count(*)
from tbl_menu
group by orderable_status;


# 두개이상 컬럼에 대해서 그룹핑 가능

select
    category_code,
    orderable_status,
    count(*)
from tbl_menu
group by category_code,orderable_status -- group by 에서는 순서 상관 없음
order by category_code;

# having 조건절
# - 그룹핑된 결과에 대한 조건절
# - where절과 달리 그룹함수 작성가능하다.
SELECT
    c.category_name,
    COUNT(*) AS menu_count
FROM tbl_menu m
         JOIN tbl_category c ON c.category_code = m.category_code
GROUP BY c.category_name
HAVING COUNT(*) >= 2;
# 카테고리별 메뉴개수가 2개이상인 카테고리(카테고리명, 개수) 조회
SELECT
    category_code,
    COUNT(*) AS menu_count
FROM tbl_menu
GROUP BY category_code
HAVING COUNT(*) >= 2;


# 카테고리별 메뉴 개수 조회 (단, category_code가 NULL인 그룹 제외)
# 일반적인 조건절
SELECT
    category_code,
    COUNT(*) AS menu_count
FROM tbl_menu
WHERE category_code IS NOT NULL   -- 그룹핑(GROUP BY) 전에 NULL 행을 먼저 제외해서, NULL 그룹이 안 생기게 함
GROUP BY category_code;

-- 이유
-- having : 그룹 생성 후 null 제거
-- where : 그룹 생성 전 null 제거

select
    c.category_name,
    count(*) cnt
from tbl_menu m
join tbl_category c on c.category_code = m.category_code
where m.category_code is not null -- 보통 불필요함(이미 매칭되었으므로)
group by c.category_code, c.category_name
having cnt>2
order by cnt;


