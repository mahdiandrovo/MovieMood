package com.drovo.moviemood

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.drovo.moviemood.data.moviedetails.MovieDetails
import com.drovo.moviemood.remote.MovieInterface
import com.drovo.moviemood.ui.details.MovieDetailsRepository
import com.drovo.moviemood.ui.movie.MoviePaging
import com.drovo.moviemood.utils.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.drovo.moviemood.utils.Result
import com.drovo.moviemood.utils.Status
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface, private val repository: MovieDetailsRepository): ViewModel() {

    private val query = MutableLiveData<String>("iron man")

    val list = query.switchMap { query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String){
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()

    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails

    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))
    }

}