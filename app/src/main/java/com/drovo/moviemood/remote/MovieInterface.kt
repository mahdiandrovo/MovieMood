package com.drovo.moviemood.remote

import com.drovo.moviemood.data.MovieResponse
import retrofit2.Response
import retrofit2.http.Query

interface MovieInterface {

    //https://www.omdbapi.com/?s=Lucifer&page=1&apikey=523f8e10
    suspend fun getAllMovies(
        @Query("s")s: String,
        @Query("page")page: Int,
        @Query("apikey")apikey: String
    ) : Response<MovieResponse>
}