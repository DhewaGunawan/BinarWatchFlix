package com.example.binarwatchflix.pkg.home.ui.homelist


import androidx.core.view.isVisible
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseViewModelFragment
import com.example.binarwatchflix.constant.ShowConstant
import com.example.binarwatchflix.databinding.FragmentHomeListBinding
import com.example.binarwatchflix.pkg.home.adapter.movie.MovieAdapter
import com.example.binarwatchflix.pkg.home.adapter.tvshow.TvShowAdapter
import com.example.binarwatchflix.pkg.home.ui.uimodel.HomeItem
import com.example.binarwatchflix.wrapper.Resource
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeListFragment(typeItem: String) :
    BaseViewModelFragment<FragmentHomeListBinding, HomeListViewModel>(FragmentHomeListBinding::inflate) {
    override val viewModel: HomeListViewModel by viewModel()

    private val movieAdapter : MovieAdapter by inject()
    private val tvShowAdapter : TvShowAdapter by inject()
    private var typeName = typeItem

    override fun initView() {
        super.initView()
        initAdapter(typeName)
        initData(typeName)
    }

    private fun initAdapter(typeName: String) {
        if (typeName == ShowConstant.MOVIES) {
            binding.rvHome.adapter = movieAdapter
        } else {
            binding.rvHome.adapter = tvShowAdapter
        }

    }

    private fun initData(typeName: String) {
        if (typeName == ShowConstant.MOVIES) {
            viewModel.getMovieData()
        } else {
            viewModel.getTvShowData()
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.movieDataResult.observe(this) {
            when (it) {
                is Resource.Empty -> showEmptyData()
                is Resource.Error -> {
                    showError()
                    setErrorMessage(it.exception?.message.orEmpty())
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> showData(it.payload)
            }
        }

        viewModel.tvShowDataResult.observe(this) {
            when (it) {
                is Resource.Empty -> showEmptyData()
                is Resource.Error -> {
                    showError()
                    setErrorMessage(it.exception?.message.orEmpty())
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> showData(it.payload)
            }
        }

    }

    private fun showLoading() {
        binding.rvHome.isVisible = false
        binding.tvErrorHome.isVisible = false
        binding.pbHome.isVisible = true
    }

    private fun showError() {
        binding.rvHome.isVisible = false
        binding.pbHome.isVisible = false
        binding.tvErrorHome.isVisible = true
    }

    private fun showData(data: List<HomeItem>?) {
        binding.rvHome.isVisible = true
        binding.pbHome.isVisible = false
        binding.tvErrorHome.isVisible = false
        if (typeName == ShowConstant.MOVIES) {
            data?.let { movieAdapter.setItems(it) }
        } else {
            data?.let { tvShowAdapter.setItems(it) }
        }

    }

    private fun showEmptyData() {
        showError()
        setErrorMessage(getText(R.string.text_empty_data).toString())
    }

    private fun setErrorMessage(msg: String) {
        binding.tvErrorHome.text = msg
    }
}