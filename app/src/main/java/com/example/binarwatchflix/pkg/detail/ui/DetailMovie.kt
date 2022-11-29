package com.example.binarwatchflix.pkg.detail.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseViewModelActivity
import com.example.binarwatchflix.constant.CommonConstant
import com.example.binarwatchflix.data.network.api.response.cast.CastItem
import com.example.binarwatchflix.data.network.api.response.movie.MovieDetailResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowDetailResponse
import com.example.binarwatchflix.databinding.ActivityDetailMovieBinding
import com.example.binarwatchflix.pkg.detail.adapter.CastAdapter
import com.example.binarwatchflix.pkg.detail.adapter.GenreAdapter
import com.example.binarwatchflix.pkg.home.ui.HomeActivity
import com.example.binarwatchflix.utils.Converter
import com.example.binarwatchflix.utils.LoadingDialog
import com.example.binarwatchflix.wrapper.Resource
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class DetailMovie : BaseViewModelActivity<ActivityDetailMovieBinding, DetailViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(intent.extras ?: bundleOf())
    }
    private val castAdapter : CastAdapter by lazy{
        CastAdapter{
            Snackbar.make(binding.root, it.name, Snackbar.LENGTH_SHORT).show()
        }
    }
    private val genreAdapter : GenreAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        initView()
        observeData()
    }

     fun initView() {

        if (viewModel.getTypeDetail() == "movie") {
            viewModel.loadMovieDetail()
            viewModel.loadCastMovie()
        } else if (viewModel.getTypeDetail() == "tvshow") {
            viewModel.loadTvShowDetail()
            viewModel.loadCastTvShow()
        }
        binding.rvCast.adapter = castAdapter
        binding.rvGenre.adapter = genreAdapter
        binding.ivArrowBack.setOnClickListener {
            HomeActivity.backToHomeActivity(this)
            finish()
        }
    }


     fun observeData() {
        viewModel.movieDetailResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //do nothing
                }
                is Resource.Error -> {
                    /* showError()
                     setErrorMessage(it.exception?.message.orEmpty())*/
                }
                is Resource.Loading -> {
                    LoadingDialog.startLoading(this)
                }
                is Resource.Success -> {
                    LoadingDialog.hideLoading()
                    it.payload?.let { detail ->
                        showDataMovie(detail)
                    }
                }
            }
        }
        viewModel.tvShowDetailResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //do nothing
                }
                is Resource.Error -> {
                    /* showError()
                     setErrorMessage(it.exception?.message.orEmpty())*/
                }
                is Resource.Loading -> {
                    LoadingDialog.startLoading(this)
                }
                is Resource.Success -> {
                    LoadingDialog.hideLoading()
                    it.payload?.let { detail ->
                        showDataTvShow(detail)
                    }

                }
            }
        }

        viewModel.castResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //do nothing
                }
                is Resource.Error -> {
                    /* showError()
                     setErrorMessage(it.exception?.message.orEmpty())*/
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.payload?.let { cast ->
                        showCast(cast.cast)
                    }

                }
            }
        }

    }



    private fun showDataTvShow(data: TvShowDetailResponse) {
        binding.ivPosterDetail.load(CommonConstant.URL_IMAGE + data.posterPath) {
            placeholder(R.drawable.ic_placeholder_poster)
        }
        binding.tvTitleDetail.text = data.name
        checkAdult(data.adult)
        loadRating(data.voteAverage)
        binding.tvDesciption.text = data.overview
        binding.ivClock.isGone = true
        genreAdapter.setData(data.genres)
    }



    private fun showDataMovie(data: MovieDetailResponse) {
        binding.ivPosterDetail.load(CommonConstant.URL_IMAGE + data.posterPath) {
            placeholder(R.drawable.ic_placeholder_poster)
        }
        binding.tvTitleDetail.text = data.name
        checkAdult(data.adult)
        loadRating(data.voteAverage)
        binding.tvDesciption.text = data.overview
        val duration = data.runTime
        binding.tvDuration.text = Converter.fromMinutesToHHmm(duration)
        genreAdapter.setData(data.genres)

    }

    private fun showCast(cast: List<CastItem>) {
        castAdapter.setData(cast)
    }

    private fun loadRating(rating: Double) {
        binding.tvRating.text = Converter.roundOffDecimal(rating)
    }

    private fun checkAdult(adult: Boolean) {
        if (adult){
            binding.tvDetailAdult.text = "18+"
        } else {
            binding.tvDetailAdult.text = "SU"
        }
    }


    companion object {
        const val EXTRAS_ID = "EXTRAS_ID"
        const val EXTRAS_TYPE = "EXTRAS_TYPE"
        fun startActivity(context: Context, id: String, type: String) {
            context.startActivity(Intent(context, DetailMovie::class.java).apply {
                putExtra(EXTRAS_ID, id)
                putExtra(EXTRAS_TYPE, type)
            })
        }
    }
}