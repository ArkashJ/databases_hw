SELECT P.name, P.dob, P.pob
FROM Person as P, Actor AS A, Movie as M
SELECT P.id=A.actor_id AND M.id=A.movie_id AND (P.name="Ryan Gosling" OR P.name="America Ferrera");
