--Q1
SELECT c.Title, t.FirstName
FROM ADcourses c
JOIN ADteachers t ON c.TeacherID = t.TeacherID;

--Q2
SELECT t.FirstName
FROM ADgroups g
JOIN ADteachers t ON g.TeacherID = t.TeacherID
WHERE g.GroupID = 'F20';

--Q3
SELECT c.CourseCode, c.Title
FROM ADcourses c
JOIN ADteachers t ON c.TeacherID = t.TeacherID
WHERE t.FirstName = 'Kanchan';

--Q4
SELECT s.FirstName, s.LastName
FROM ADstudents s
WHERE s.GroupID = 'F21'
AND NOT EXISTS (
    SELECT 1
    FROM students_courses sc
    WHERE sc.StudentID = s.StudentID
);

--Q5
SELECT FirstName, LastName
FROM ADstudents
WHERE DOB = (SELECT MIN(DOB) FROM ADstudents);

--Q6
SELECT sc.StudentID, s.FirstName
FROM students_courses sc
JOIN ADstudents s ON sc.StudentID = s.StudentID
WHERE sc.NumericalGrade < 50 OR sc.NumericalGrade IS NULL;

--Q8
SELECT s.FirstName, s.StudentID, s.Phone
FROM ADstudents s
LEFT JOIN students_courses sc ON s.StudentID = sc.StudentID
WHERE sc.NumericalGrade IS NULL;

--Q9
SELECT t.FirstName, t.TeacherID
FROM ADteachers t
JOIN ADcourses c ON t.TeacherID = c.TeacherID
WHERE c.Title = 'Database Concepts';

--Q10
SELECT s.FirstName, sc.NumericalGrade
FROM ADstudents s
JOIN students_courses sc ON s.StudentID = sc.StudentID
JOIN ADcourses c ON sc.CourseCode = c.CourseCode
WHERE c.Title = 'Web Programming';

--Q11
SELECT s.StudentID, s.FirstName, s.LastName
FROM ADstudents s
JOIN students_courses sc ON s.StudentID = sc.StudentID
JOIN ADcourses c ON sc.CourseCode = c.CourseCode
WHERE c.Title = 'Intro to Programming' AND sc.NumericalGrade >= 75;

--12A
SELECT s.FirstName, s.LastName, s.GroupID
FROM ADstudents s
WHERE s.StudentID = (
    SELECT sc.StudentID
    FROM (
        SELECT sc.StudentID, AVG(sc.NumericalGrade) AS AvgGrade
        FROM students_courses sc
        JOIN ADcourses c ON sc.CourseCode = c.CourseCode
        WHERE c.Title = 'AD006'
        GROUP BY sc.StudentID
        HAVING AVG(sc.NumericalGrade) = (
            SELECT MAX(AvgGrade)
            FROM (
                SELECT AVG(sc.NumericalGrade) AS AvgGrade
                FROM students_courses sc
                JOIN ADcourses c ON sc.CourseCode = c.CourseCode
                WHERE c.Title = 'AD006'
                GROUP BY sc.StudentID
            )
        )
    ) HighestAvgGrade
    JOIN students_courses sc ON HighestAvgGrade.StudentID = sc.StudentID
    WHERE sc.NumericalGrade >= 50
);
