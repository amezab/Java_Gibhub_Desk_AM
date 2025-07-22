-- 1. Select all rows from Building table
SELECT * FROM Building;

-- 2. Period name, start, and end times
SELECT PeriodName, StartTime, EndTime FROM Period;

-- 3. Check which table is empty (run each to see which returns no rows)
SELECT * FROM Building;
SELECT * FROM Room;
SELECT * FROM Course;
SELECT * FROM Teacher;
SELECT * FROM Period;
SELECT * FROM GradeType;
SELECT * FROM GradeItem;

-- 4. Courses and credit hours in format: CourseName (CreditHours)
SELECT CONCAT(CourseName, ' (', CreditHours, ')') AS CourseInfo FROM Course;

-- 5. Teachers' full names (first name and last initial) for first five
SELECT CONCAT(FirstName, ' ', LEFT(LastName, 1), '.') AS FullName 
FROM Teacher 
LIMIT 5;

-- 6. How many rooms are there?
SELECT COUNT(*) AS TotalRooms FROM Room;

-- 7. Range of room numbers
SELECT MIN(RoomNumber) AS MinRoom, MAX(RoomNumber) AS MaxRoom FROM Room;

-- 8. Examine Description field
SELECT Description FROM Room;

-- 9. How many unique SubjectIDs in Course table
SELECT COUNT(DISTINCT SubjectID) AS UniqueSubjects FROM Course;

-- 10. How many grade types are there?
SELECT COUNT(*) AS TotalGradeTypes FROM GradeType;

-- 11. IDs and Names of grade types
SELECT GradeTypeID AS ID, GradeTypeName AS Name FROM GradeType;

-- 12. What grade types appear in GradeItem table?
SELECT DISTINCT GradeTypeID FROM GradeItem;