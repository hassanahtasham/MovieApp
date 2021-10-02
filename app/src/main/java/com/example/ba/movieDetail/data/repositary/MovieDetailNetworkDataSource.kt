package com.example.ba.movieDetail.data.repositary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ba.movieDetail.data.api.TheMovieDBInterface
import com.example.ba.movieDetail.data.vo.movieDetailModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class MovieDetailNetworkDataSource(private val api: TheMovieDBInterface, private val compositeDisposable: CompositeDisposable) {

    val apiKey = "58102d2d3bf30ece4fda298300b15768"
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
    get() = _networkState

    private val _downloadMovieDetailResponse = MutableLiveData<movieDetailModel>()
    val downloadMovieDetailResponse : LiveData<movieDetailModel>
    get() = downloadMovieDetailResponse

    fun fetchMovieDetail(movieId: Int){
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                    api.getMovieDetail(movieId, apiKey)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {
                                        _downloadMovieDetailResponse.postValue(it)
                                        _networkState.postValue(NetworkState.LOADED)
                                    },

                                    {
                                        _networkState.postValue(NetworkState.ERROR)
                                        Log.e( "fetchMovieDetail: ", it.message.toString())
                                    }
                            )

            )

        }catch(e: Exception){

            Log.e( "fetchMovieDetail: ", e.message.toString())
        }
    }

}