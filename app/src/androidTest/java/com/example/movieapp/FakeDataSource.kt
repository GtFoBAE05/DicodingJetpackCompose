package com.example.movieapp

import com.example.movieapp.data.remote.response.PopularMoviesResult

object FakeDataSource {
    fun generateDummyList() : List<PopularMoviesResult>{
        val list = ArrayList<PopularMoviesResult>()
        for(i in 0..100){
            val item = PopularMoviesResult(
                id = i,
                posterPath = "posterpath $i",
                title = "title $i",
                voteAverage = i.toDouble()
            )
            list.add(item)
        }
        return list
    }
}