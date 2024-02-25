#
# CS 460: Lab 2, Task 2
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
SELECT name, capacity
FROM Room
WHERE name like 'CAS%' OR name like 'CGS%'
"""

#
# Query 2. Put your SQL command between the triple quotes found below.
#
query2 = """
SELECT Count(DISTINCT student_id)
FROM Enrolled
WHERE credit_status="ugrad"
"""

#
# Query 3. Put your SQL command between the triple quotes found below.
#
query3 = """ 
SELECT min(start_time)
FROM Course
WHERE name like 'CAS%'
"""

#
# Query 4. Put your SQL command between the triple quotes found below.
#
query4 = """

"""

#
# Query 5. Put your SQL command between the triple quotes found below.
#
query5 = """
SELECT credit_status, COUNT(DISTINCT student_id)           
FROM Enrolled
GROUP BY credit_status
"""

#
# Query 6. Put your SQL command between the triple quotes found below.
#
query6 = """
SELECT name
FROM Student, MajorsIn
Where id = student_id and dept_name = 'Computer Science'
"""

#
# Query 7. Put your SQL command between the triple quotes found below.
#
query7 = """
SELECT name 
FROM Student 
WHERE id NOT IN (SELECT student_id FROM Enrolled WHERE course_name = 'CS 460')
"""


# Lab 2
# Question 1
CREATE TABLE WorksFor(
    student_id CHAR(9) PRIMARY KEY,
    dept_name VARCHAR(20),
    job_title VARCHAR(30),
    FOREIGN KEY(student_id) REFERENCES Student(id),
    FOREIGN KEY(dept_name) REFERENCES Department(name)
)

# Question 2
# insert data into WorksFor
INSERT INTO WorksFor
VALUES(
    'U12345678',
    'Computer Science',
    'TA'
)
