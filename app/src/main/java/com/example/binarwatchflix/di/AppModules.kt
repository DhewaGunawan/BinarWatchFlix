package com.example.binarwatchflix.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.binarwatchflix.data.network.api.Repository
import com.example.binarwatchflix.data.network.api.RepositoryImpl
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSource
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSourceImpl
import com.example.binarwatchflix.data.network.api.service.TmdbApiService
import com.example.binarwatchflix.pkg.home.adapter.movie.MovieAdapter
import com.example.binarwatchflix.pkg.home.adapter.tvshow.TvShowAdapter
import com.example.binarwatchflix.pkg.home.ui.homelist.HomeListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    fun getModules(): List<Module> = listOf(
        networkModule, dataSource, repository, viewModels , adapter
    )

    private val networkModule = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() } //singleTon
        single { TmdbApiService.invoke(get())}
    }

    private val dataSource = module {
        single<TmdbApiDataSource> { TmdbApiDataSourceImpl(get()) }
    }

    private val repository = module {
        single<Repository> { RepositoryImpl(get()) }
    }

    private val adapter = module {
        //jika dipanggil harus instance yang baru, bukan single instance
        factory { MovieAdapter()  }
        factory { TvShowAdapter()  }
    }

    private val viewModels = module {
        viewModel { HomeListViewModel(get()) }
    }
}