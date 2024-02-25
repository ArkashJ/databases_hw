#
# CS 460: Problem Set 2: XQuery Programming Problems
#

#
# For each query, use a text editor to add the appropriate XQuery
# command between the triple quotes provided for that query's variable.
#
# For example, here is how you would include a query that finds
# the names of all movies in the database from 1990.
#
sample = """
    for $m in //movie
    where $m/year = 1990
    return $m/name
"""

#
# 1. Put your query for this problem between the triple quotes found below.
#    Follow the same format as the model query shown above.
#
query1 = """
//person[name[starts-with(., 'Sam ')]] 
"""

#
# 2. Put your query for this problem between the triple quotes found below.
#


query2 = """
for $oscar in doc("imdb.xml")//oscars/oscar
let $person_oscars := doc("imdb.xml")//person[name="Jodie Foster"]/@oscars,
 $movie := doc("imdb.xml")//movie[@id=$oscar/@movie_id]
where contains($person_oscars, $oscar/@id) and $oscar/type="BEST-ACTRESS"
return <foster_oscar>
  <name>{$movie/name}</name>
  <type>{$oscar/type}</type>
  <year>{$movie/year}</year>
</foster_oscar>
"""

#
# 3. Put your query for this problem between the triple quotes found below.
#
query3 = """
for $year in distinct-values(doc("imdb.xml")//movie/year)
let $movies := doc("imdb.xml")//movie[year = $year]
where $year>=2010
order by $year
return
  <runtime_info>
    <year>{$year}</year>
    <average>{avg($movies/runtime)}</average>
    <longest>{max($movies/runtime)}</longest>
    <shortest>{min($movies/runtime)}</shortest>
  </runtime_info>
"""

#
# 4. Put your query for this problem between the triple quotes found below.
#
query4 = """
for $d in doc("imdb.xml")//person[@directed]
let $directorId := $d/@id,
    $movies := doc("imdb.xml")//movie[contains(@directors, $directorId) and earnings_rank < 200]
where count($movies) >= 4
return <top_director>
      <name>{$d/name}</name>
      <num_top_grossers>{count($movies)}</num_top_grossers>
    {
      for $m in $movies
      let $movie_rank := string($m/earnings_rank), $movie_name := string($m/name)
      return <movie>{$movie_name || '-' || $movie_rank}</movie>
    }
</top_director>
"""

#
# 5. Put your query for this problem between the triple quotes found below.
#
query5 = """
for $m in doc("imdb.xml")//movie[contains(genre, "B")]
return <biopic>
  <name>{string($m/name)}</name>
  <year>{string($m/year)}</year>
  {
    for $o in doc("imdb.xml")//oscar
    where $o/@movie_id = $m/@id
    return <award>{string($o/type)}</award>
  }
</biopic>
"""
