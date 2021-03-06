package com.example.ba.movieDetail.data.repositary

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState (val status: Status, val message: String){

     companion object{

         val LOADED: NetworkState
         val LOADING: NetworkState
         val ERROR: NetworkState

         init {
             LOADED = NetworkState(Status.SUCCESS, "Successful")
             LOADING = NetworkState(Status.RUNNING, "Running")
             ERROR = NetworkState(Status.FAILED, "Error")
         }
    }
}