package com.example.ba.movieDetail.data.api

import com.example.ba.movieDetail.data.vo.movieDetailModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/{movie_id}")
    fun getMovieDetail (@Path ("movie_id") id: Int, @Query("api_key") apiKey: String): Single<movieDetailModel>



}