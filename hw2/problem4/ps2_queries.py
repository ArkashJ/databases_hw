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
for $p in //person[name[starts-with(., 'Sam ')]] 
return $p/name
"""

#
# 2. Put your query for this problem between the triple quotes found below.
#


query2 = """
for $oscar in //oscars/oscar
let $person_oscars := //person[name="Jodie Foster"]/@oscars,
 $movie := //movie[@id=$oscar/@movie_id]
where contains($person_oscars, $oscar/@id) and $oscar/type="BEST-ACTRESS"
return <foster_oscar>
  {$movie/name}
  {$oscar/type}
  {$oscar/year}
</foster_oscar>
"""

#
# 3. Put your query for this problem between the triple quotes found below.
#
query3 = """
for $year in distinct-values(//movie/year)
let $movies := //movie[year = $year]
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
for $d in //person[@directed]
let $directorId := $d/@id,
    $movies := //movie[contains(@directors, $directorId) and earnings_rank < 200]
where count($movies) >= 4
return <top_director>
      {$d/name}
      {count($movies)}
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
for $m in //movie[contains(genre, "B")]
return <biopic>
  <name>{string($m/name)}</name>
  <year>{string($m/year)}</year>
  {
    for $o in //oscar
    where $o/@movie_id = $m/@id
    return <award>{string($o/type)}</award>
  }
</biopic>
"""
