package com.drovo.moviemood.ui.details

import com.drovo.moviemood.data.moviedetails.MovieDetails
import com.drovo.moviemood.remote.MovieInterface
import com.drovo.moviemood.utils.Constants
import com.drovo.moviemood.utils.Result
import com.drovo.moviemood.utils.Status

class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): Result<MovieDetails>{
        return try {
            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)
            if (response.isSuccessful){
                Result(Status.SUCCESS, response.body(), null)
            } else{
                Result(Status.ERROR, null, null)
            }

        } catch (e: Exception){
            Result(Status.ERROR, null, null)
        }
    }
}