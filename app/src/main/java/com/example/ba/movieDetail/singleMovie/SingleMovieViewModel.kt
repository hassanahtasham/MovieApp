package com.example.ba.movieDetail.singleMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ba.movieDetail.data.repositary.NetworkState
import com.example.ba.movieDetail.data.vo.movieDetailModel
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(val movieDetailRepositary: SingleMovieDetailRepositary,val movieID: Int): ViewModel() {

    private val compositeDisposable= CompositeDisposable()

    val movieDetail: LiveData<movieDetailModel> by lazy {
        movieDetailRepositary.fetchingMovieDetails(compositeDisposable, movieID)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailRepositary.getMovieDetailNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}