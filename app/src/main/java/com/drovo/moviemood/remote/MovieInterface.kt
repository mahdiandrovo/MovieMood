package com.drovo.moviemood.remote

import com.drovo.moviemood.data.MovieResponse
import com.drovo.moviemood.data.moviedetails.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    //https://www.omdbapi.com/?s=Lucifer&page=1&apikey=523f8e10

    @GET("/")
    suspend fun getAllMovies(
        @Query("s")s: String,
        @Query("page")page: Int,
        @Query("apikey")apikey: String
    ) : Response<MovieResponse>

    //for a specific movie
    //https://www.omdbapi.com/?i=tt4052886&apikey=523f8e10
    @GET("/")
    suspend fun getMovieDetails(
        @Query("i")imdbId: String,
        @Query("apikey")apikey: String
    ) : Response<MovieDetails>
}