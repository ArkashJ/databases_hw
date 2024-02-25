#
# CS 460: Problem Set 1, SQL Programming Problems
#

#
# For each problem, use a text editor to add the appropriate SQL
# command between the triple quotes provided for that problem's variable.
#
# For example, here is how you would include a query that finds the
# names and years of all movies in the database with an R rating:
#
sample = """
SELECT name, dob, pob
FROM Person
WHERE name="Ryan Gosling" or name = "America Ferrera"
"""

#
# Problem 4. Put your SQL command between the triple quotes found below.
#
problem4 = """
SELECT P.name, P.dob, P.pob
FROM Person as P, Actor AS A, Movie as M
WHERE P.id=A.actor_id AND M.id=A.movie_id  AND (P.name="Ryan Gosling" OR P.name="America Ferrera") 
"""
#
# Problem 5. Put your SQL command between the triple quotes found below.
#
problem5 = """
SELECT rating, count(rating)
FROM Movie as M
WHERE M.runtime>180 and M.rating IS NOT NULL
GROUP BY M.rating"""

#
# Problem 6. Put your SQL command between the triple quotes found below.
#
problem6 = """
SELECT M.name, O.type, O.year
FROM Person as P, Movie as M, Oscar as O
WHERE P.id=O.person_id AND M.id=O.movie_id AND P.name="Jodie Foster"
"""

#
# Problem 7. Put your SQL command between the triple quotes found below.
#
problem7 = """
SELECT count(DISTINCT D.director_id)
FROM Person AS PAct, Actor AS A, Director AS D, Person AS PDir
WHERE PAct.id=A.actor_id AND PDir.id=D.director_id AND A.movie_id=D.movie_id AND PAct.pob NOT LIKE "%USA" AND PDir.pob NOT LIKE "%USA" AND PAct.pob IS NOT NULL And PDir.pob IS NOT NULL;
"""
# AND PDir.pob IS NOT NULL And PAct.pob IS NOT NULL

#
# Problem 8. Put your SQL command between the triple quotes found below.
#
problem8 = """
SELECT M.year, Avg(M.runtime) AS average_runtime
FROM Movie as M
WHERE M.year>=2010
GROUP BY M.year
ORDER BY M.year;
"""

#
# Problem 9. Put your SQL command between the triple quotes found below.
#
problem9 = """
SELECT DISTINCT M.name, M.year
FROM Movie AS M, Oscar AS O
WHERE M.id=O.movie_id AND O.type="BEST-PICTURE" AND M.name LIKE "The %"
"""

#
# Problem 10. Put your SQL command between the triple quotes found below.
#
problem10 = """
SELECT count(*)
FROM Movie AS M
WHERE runtime <(SELECT min(M2.runtime) FROM Movie as M2, Oscar as O  WHERE M2.id=O.movie_id AND O.type="BEST-PICTURE")           
"""

#
# Problem 11. Put your SQL command between the triple quotes found below.
#
problem11 = """
SELECT P.name, COUNT(M.id)
FROM Director AS D, Person as P, Movie as M
WHERE M.earnings_rank<=200 AND P.id=D.director_id AND M.id=D.movie_id
GROUP BY P.id
HAVING COUNT(M.id)>=4
ORDER BY COUNT(M.id) DESC;           
"""


problem12 = """
SELECT count (DISTINCT actor_id)
FROM Actor
WHERE actor_id NOT IN (
SELECT Actor.actor_id
From Actor, Movie
WHERE Actor.movie_id = Movie.id and Movie.earnings_rank <= 200 )
"""
#
# Problem 13. Put your SQL command between the triple quotes found below.
#
problem13 = """
SELECT M.name, O.type
FROM Movie as M LEFT OUTER JOIN Oscar as O on M.id=O.movie_id
WHERE M.genre LIKE "%B%"
"""

#
# Problem 14. Put your SQL command between the triple quotes found below.
#
problem14 = """
SELECT M.name, M.year
FROM Movie M
WHERE M.year = (
    SELECT MAX(M2.year)
    FROM Movie M2
    WHERE M2.rating = 'G'
)
AND M.rating = 'G';
"""

#
# Problem 15. Put your SQL command between the triple quotes found below.
#
problem15 = """
UPDATE Movie 
SET rating="PG-13"
WHERE Movie.name="Indiana Jones and the Temple of Doom"
"""


