package com.example.ba.movieDetail.data.vo


import com.google.gson.annotations.SerializedName

data class movieDetailModel(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    @SerializedName("vote_average")
    val rating: Double
)