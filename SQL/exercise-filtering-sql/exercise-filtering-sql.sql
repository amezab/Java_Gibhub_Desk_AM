-- 1. Students with LastName starting with "Cr"
SELECT StudentID, LastName, FirstName 
FROM Student 
WHERE LastName LIKE 'Cr%';

-- 2. Courses with SubjectIDs 1, 2, 4 using OR
SELECT * FROM Course 
WHERE SubjectID = 1 OR SubjectID = 2 OR SubjectID = 4;

-- 3. Courses with SubjectIDs 1, 2, 4 using IN
SELECT * FROM Course 
WHERE SubjectID IN (1, 2, 4);

-- 4. Student record with id of 42
SELECT * FROM Student 
WHERE StudentID = 42;

-- 5. Student FirstNames starting with "C" using LIKE
SELECT FirstName FROM Student 
WHERE FirstName LIKE 'C%';

-- 6. Student FirstNames starting with "Ce" using BETWEEN
SELECT FirstName FROM Student 
WHERE FirstName BETWEEN 'Ce' AND 'Cf';

-- 7. First 10 unique Student LastNames
SELECT DISTINCT LastName FROM Student 
LIMIT 10;

-- 8. First 10 Student records
SELECT * FROM Student 
LIMIT 10;

-- 9. Top five Students in reverse alphabetical order by LastName
SELECT * FROM Student 
ORDER BY LastName DESC 
LIMIT 5;