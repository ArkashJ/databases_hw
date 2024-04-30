#
# CS 460: Problem Set 5: MongoDB Query Problems
#

#
# For each query, use a text editor to add the appropriate XQuery
# command between the triple quotes provided for that query's variable.
#
# For example, here is how you would include a query that finds
# the names of all movies in the database from 1990.
#
sample = """
    db.movies.find( { year: 1990 }, 
                    { name: 1, _id: 0 } )
"""

#
# 1. Put your query for this problem between the triple quotes found below.
#    Follow the same format as the model query shown above.
#
query1 = """
db.people.find({$or: [{name: "Ryan Gosling"}, {name: "America Ferrera"}]}, {name: 1, dob: 1, pob: 1, _id: 0})
"""

#
# 2. Put your query for this problem between the triple quotes found below.
#
query2 = """
db.movies.find({rating: {$exists: false}, year: {$gte: 1960}}, {name:1, year:1, _id:0})
"""

#
# 3. Put your query for this problem between the triple quotes found below.
#
query3 = """
db.oscars.distinct("movie.name", {"movie.name": /^The /})
"""

#
# 4. Put your query for this problem between the triple quotes found below.
#
query4 = """
db.movies.aggregate(
{$match: {rating: "G"}},
{$sort: {year:-1} },
{$limit: 1},
{$project: {_id:0, name:1, year:1}}
)
"""

#
# 5. Put your query for this problem between the triple quotes found below.
#
query5 = """
db.people.find({hasActed: true, pob: /Mexico$/},{name:1, pob:1, _id:0})
"""

#
# 6. Put your query for this problem between the triple quotes found below.
#
query6 = """
db.movies.count({rating: "R", runtime: {$gte: 120, $lte: 180}})
"""

#
# 7. Put your query for this problem between the triple quotes found below.
#
query7 = """
db.movies.aggregate(
{$match: {runtime: {$gte:120, $lte:180}, rating: {$nin: [null]}}},
{$group: {_id: "$rating", num_2_3_hours: {$sum: 1}, shortest: {$min: "$runtime"}, longest: {$max: "$runtime"}}},
{$sort: {_id: 1}},
{$project: {_id: 0, num_2_3_hours:1, shortest: 1, longest:1, rating:"$_id"}}
)
"""

#
# 8. Put your query for this problem between the triple quotes found below.
#
query8 = """
db.people.aggregate({$match: {hasDirected: true, pob: /, USA/}}, {$group: {_id: "$pob", num_directors: {$sum:1}}}, 
{$match: {num_directors: {$gte: 5}}}, 
{$sort: {num_directors: -1}},
{$project: {_id: 0, pob: "$_id", num_directors: 1}}
)"""

#
# 9. Put your query for this problem between the triple quotes found below.
#
query9 = """
db.movies.aggregate(
{$match: {"directors.name": "Christopher Nolan"}},
{$group: {_id: "$directors.name", num_movies: {$sum: 1}, top_rank: {$min: "$earnings_rank"}, movies: {$addToSet: "$name"}, avg_runtime: {$avg: "$runtime"}}},
{$project: {_id: 0}}
)
"""

#
# 10. Put your query for this problem between the triple quotes found below.
#
query10 = """
db.movies.aggregate(
  {$unwind: "$directors"},
  {$match: {earnings_rank: {$exists: true}}},
  {$group: {_id: "$directors.name", num_top_grossers: {$sum: 1}}},
  {$sort: {num_top_grossers: -1, _id: 1}},
  {$limit: 4},
  {$project: {_id: 0, num_top_grossers: 1, director: "$_id"}}
)
"""
