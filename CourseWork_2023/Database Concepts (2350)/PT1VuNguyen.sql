PRACTICAL TEST 1
NAME: Vu Nguyen
ID: s4010423


Q1
SELECT FIRSTNAME, LASTNAME, GROUPID
FROM ADSTUDENTS
WHERE FIRSTNAME = 'Frodo' AND LASTNAME = 'Baggins';

Q2
SELECT TEACHERID
FROM ADCOURSES
WHERE COURSECODE = 'ISYS2390';

Q3
SELECT *
FROM ADSTUDENTS
WHERE EMAIL NOT LIKE '%@%';

Q4
SELECT ADstudents.FirstName, ADstudents.Email, ADstudents.Phone
FROM ADstudents
JOIN ADgroups ON ADstudents.GroupID = ADgroups.GroupID;

Q5
SELECT AVG(NumericalGrade) AS AverageGrade
FROM students_courses
WHERE StudentID = 12345672;

Q6
SELECT StudentID, CourseCode
FROM students_courses
WHERE NumericalGrade IS NULL;

Q7
SELECT CourseCode, Title
FROM ADcourses
WHERE CourseCode LIKE 'COSC%';

Q8
SELECT
    c.CourseCode,
    MIN(sc.NumericalGrade) AS LowestGrade,
    MAX(sc.NumericalGrade) AS HighestGrade,
    ROUND(AVG(sc.NumericalGrade), 2) AS AverageGrade
FROM
    ADcourses c
JOIN
    students_courses sc ON c.CourseCode = sc.CourseCode
GROUP BY
    c.CourseCode;

Q9
SELECT COUNT(*) AS CompletedStudents
FROM students_courses
WHERE CourseCode = 'COSC2385' AND NumericalGrade >= 50;

Q10
SELECT StudentID, ROUND(AVG(NumericalGrade)) AS AverageGrade
FROM students_courses
GROUP BY StudentID
HAVING ROUND(AVG(NumericalGrade)) >= 80;

Q11
SELECT t.TeacherID, SUM(tc.Hours) AS TotalHours
FROM ADteachers t
JOIN teachers_courses tc ON t.TeacherID = tc.TeacherID
GROUP BY t.TeacherID
ORDER BY TotalHours DESC, t.TeacherID ASC;

Q12
SELECT GroupID, COUNT(*) AS EnrolledStudents
FROM ADstudents
GROUP BY GroupID
HAVING COUNT(*) > 3;

Q13
SELECT
    sc.StudentID,
    SUM(1000) AS "Fees Paid - Student 12345673"
FROM students_courses sc
WHERE sc.StudentID = 12345673
GROUP BY sc.StudentID;

Q14
SELECT
    COUNT(DISTINCT tc.TeacherID) AS "Number of Teachers",
    SUM(tc.Hours) AS "Number of Hours"
FROM
    teachers_courses tc
WHERE
    tc.CourseCode IN ('COSC2511', 'COSC2446', 'COSC2384', 'ISYS2390');





