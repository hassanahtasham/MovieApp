package com.example.ba.movieDetail.singleMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStores.of
import com.bumptech.glide.Glide
import com.example.ba.R
import com.example.ba.movieDetail.data.api.ServiceGenerator
import com.example.ba.movieDetail.data.api.TheMovieDBInterface
import com.example.ba.movieDetail.data.repositary.NetworkState
import com.example.ba.movieDetail.data.vo.movieDetailModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Invocation.of
import java.util.EnumSet.of

class movieDetail : AppCompatActivity() {

    private lateinit var viewmodel: SingleMovieViewModel
    private lateinit var singleMovieDetailRepositary: SingleMovieDetailRepositary
    val poster = "https://cinemazero.it/media/photologue/photos/temp/or06FN3Dka5tukK1e9sl16pB3iy.jpg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieID: Int = 299534
        val api: TheMovieDBInterface = ServiceGenerator.getApi()
        singleMovieDetailRepositary = SingleMovieDetailRepositary(api)

        viewmodel = getViewModel(movieID)


        viewmodel.movieDetail.observe(this, Observer {
            bindUI(it)
        })

//        viewmodel.networkState.observe(this, {
//            progress_bar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
//            txt_error.visibility = if(it== NetworkState.ERROR) View.VISIBLE else View.GONE
//
//        })


    }

    fun bindUI(it: movieDetailModel){

        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_budget.text = it.budget.toString()
        movie_overview.text = it.overview
        movie_rating.text = it.rating.toString()
        movie_release_date.text = it.releaseDate
        movie_revenue.text = it.revenue.toString()


        Glide.with(this)
                .load(poster)
                .into(iv_movie_poster)


    }

    private fun getViewModel(movieID: Int): SingleMovieViewModel{


        return ViewModelProviders.of(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(singleMovieDetailRepositary,movieID) as T
            }


        })[SingleMovieViewModel::class.java]

    }
}