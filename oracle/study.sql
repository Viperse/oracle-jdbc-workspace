SELECT DEPARTMENT_NAME AS "학과 명", CATEGORY AS "계열" -- 1번
FROM TB_DEPARTMENT;

SELECT DEPARTMENT_NAME || '의 정원은 ' || CAPACITY || '명 입니다' AS "학과별 정원"  -- 2번
FROM TB_DEPARTMENT;

SELECT STUDENT_NAME  -- 3번
FROM TB_STUDENT
WHERE DEPARTMENT_NO = '001' AND STUDENT_SSN LIKE '_______2%' AND ABSENCE_YN = 'Y'; 

SELECT STUDENT_NAME  -- 4번
FROM TB_STUDENT
WHERE STUDENT_NO IN('A513079', 'A513090', 'A513091', 'A513110', 'A513119');

SELECT DEPARTMENT_NAME, CATEGORY -- 5번
FROM TB_DEPARTMENT
WHERE CAPACITY BETWEEN 20 AND 30;

SELECT PROFESSOR_NAME -- 6번
FROM TB_PROFESSOR
WHERE DEPARTMENT_NO IS NULL;

SELECT STUDENT_NAME -- 7번
FROM TB_STUDENT
WHERE DEPARTMENT_NO IS NULL;

SELECT CLASS_NO -- 8번
FROM TB_CLASS
WHERE PREATTENDING_CLASS_NO IS NOT NULL;

SELECT DISTINCT CATEGORY -- 9번
FROM TB_DEPARTMENT;

SELECT STUDENT_NO, STUDENT_NAME, STUDENT_SSN -- 10번
FROM TB_STUDENT
WHERE ENTRANCE_DATE LIKE '02%' AND STUDENT_ADDRESS LIKE '%전주%';