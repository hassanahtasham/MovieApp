package com.example.ba.movieDetail.data.api

import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object ServiceGenerator {

    const val  baseUrl = "https://api.themoviedb.org/3/"
    const val apiKey = "58102d2d3bf30ece4fda298300b15768"
    const val poster = "https://cinemazero.it/media/photologue/photos/temp/or06FN3Dka5tukK1e9sl16pB3iy.jpg"

    var spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build()

    var client = OkHttpClient.Builder()
            .connectionSpecs(Collections.singletonList(spec))
            .build()


    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()
    private val api: TheMovieDBInterface = retrofit.create(TheMovieDBInterface::class.java)
    fun getApi(): TheMovieDBInterface? {
        return api
    }
}