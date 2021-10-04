package com.example.ba.movieDetail.data.api

import com.example.ba.BuildConfig
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import java.util.*
import java.util.concurrent.TimeUnit


object ServiceGenerator {

    const val  baseUrl = "https://api.themoviedb.org/3/"
    const val apiKey = "58102d2d3bf30ece4fda298300b15768"
    const val poster = "https://cinemazero.it/media/photologue/photos/temp/or06FN3Dka5tukK1e9sl16pB3iy.jpg"

    fun getClient(): TheMovieDBInterface {


        val requestInterceptor = Interceptor { chain ->
            // Interceptor take only one argument which is a lambda function so parenthesis can be omitted

            val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()

            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

            return@Interceptor chain.proceed(request)   //explicitly return a value from whit @ annotation. lambda always returns the value of the last expression implicitly
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieDBInterface::class.java)

    }
}