package com.example.ba.movieDetail.singleMovie

import androidx.lifecycle.LiveData
import com.example.ba.movieDetail.data.api.TheMovieDBInterface
import com.example.ba.movieDetail.data.repositary.MovieDetailNetworkDataSource
import com.example.ba.movieDetail.data.repositary.NetworkState
import com.example.ba.movieDetail.data.vo.movieDetailModel
import io.reactivex.disposables.CompositeDisposable

class SingleMovieDetailRepositary(private val api: TheMovieDBInterface) {

    lateinit var movieDetailNetworkDataSource: MovieDetailNetworkDataSource

    fun fetchingMovieDetails(compositeDisposable: CompositeDisposable, movieID: Int): LiveData<movieDetailModel>{

        movieDetailNetworkDataSource = MovieDetailNetworkDataSource(api, compositeDisposable)
        movieDetailNetworkDataSource.fetchMovieDetail(movieID)

        return movieDetailNetworkDataSource.downloadMovieDetailResponse
    }

    fun getMovieDetailNetworkState(): LiveData<NetworkState>{
        return movieDetailNetworkDataSource.networkState
    }

}