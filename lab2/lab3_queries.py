#
# CS 460: Lab 3, Task 2
#

#
# Sample query
#
sample = """
    SELECT name
    FROM Course
    WHERE room_id IS NULL;
"""

#
# Query 1. Put your SQL command between the triple quotes found below.
# Follow the same format as the sample query shown above.
#
query1 = """
SELECT Student.name, MajorsIn.dept_name
FROM STUDENT, MajorsIn
WHERE Student.id = MajorsIn.student_id
"""

#
# Query 2. Put your SQL command between the triple quotes found below.
#
query2 = """
SELECT S.name, M.dept_name
FROM STUDENT as S LEFT OUTER JOIN MajorsIn as M on S.name 
WHERE S.id = M.student_id 

"""

#
# Query 3. Put your SQL command between the triple quotes found below.
#
query3 = """ 

SELECT S.name, M.dept_name
FROM STUDENT as S LEFT OUTER JOIN MajorsIn as M on S.name LIKE 'C%' OR S.name LIKE 'S%'
WHERE S.id = M.student_id 
"""

#
# Query 4. Put your SQL command between the triple quotes found below.
#
query4 = """
SELECT M.dept_name, count(S.id)
FROM Student as S, MajorsIn as M
WHERE S.id = M.student_id 
GROUP By M.dept_name
"""

#
# Query 5. Put your SQL command between the triple quotes found below.
#
query5 = """
SELECT M.dept_name, COUNT(M.dept_name) AS student_count
FROM Student AS S LEFT OUTER JOIN MajorsIn AS M ON S.id = M.student_id
GROUP BY M.dept_name
ORDER BY student_count DESC;
"""

#
# Query 6. Put your SQL command between the triple quotes found below.
#
query6 = """
SELECT M.dept_name, COUNT(M.dept_name) AS student_count
FROM Student AS S LEFT OUTER JOIN MajorsIn AS M ON S.id = M.student_id
GROUP BY M.dept_name
HAVING Count(M.dept_name)<2
ORDER BY student_count DESC;
"""

#
# Query 7. Put your SQL command between the triple quotes found below.
#
query7 = """
SELECT R.name, R.capacity
FROM Room as R
WHERE R.capacity = (SELECT min(capacity) FROM Room)
"""

#
# Query 8. Put your SQL command between the triple quotes found below.
#
query8 = """

"""

#
# Query 9. Put your SQL command between the triple quotes found below.
#
query9 = """

"""

#
# Query 10. Put your SQL command between the triple quotes found below.
#
query10 = """

"""

#
# Query 11. Put your SQL command between the triple quotes found below.
#
query11 = """

"""

#
# Query 12. Put your SQL command between the triple quotes found below.
#
query12 = """

"""


