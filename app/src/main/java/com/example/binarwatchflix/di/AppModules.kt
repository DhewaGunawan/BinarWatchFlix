package com.example.binarwatchflix.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.binarwatchflix.BuildConfig
import com.example.binarwatchflix.data.firebase.*
import com.example.binarwatchflix.data.network.api.Repository
import com.example.binarwatchflix.data.network.api.RepositoryImpl
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSource
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSourceImpl
import com.example.binarwatchflix.data.network.api.service.TmdbApiService
import com.example.binarwatchflix.data.repository.ChatRepository
import com.example.binarwatchflix.data.repository.ChatRepositoryImpl
import com.example.binarwatchflix.pkg.auth.AuthViewModel
import com.example.binarwatchflix.pkg.chat.ui.ChatViewModel
import com.example.binarwatchflix.pkg.detail.adapter.CastAdapter
import com.example.binarwatchflix.pkg.detail.adapter.GenreAdapter
import com.example.binarwatchflix.pkg.detail.ui.DetailViewModel
import com.example.binarwatchflix.pkg.home.adapter.movie.MovieAdapter
import com.example.binarwatchflix.pkg.home.adapter.tvshow.TvShowAdapter
import com.example.binarwatchflix.pkg.home.ui.homelist.HomeListViewModel
import com.example.binarwatchflix.pkg.splashscreen.SplashViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    fun getModules(): List<Module> = listOf(
        networkModule, dataSource, repository, viewModels , adapter, firebase
    )

    private val networkModule = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() } //singleTon
        single { TmdbApiService.invoke(get())}
    }

    private val dataSource = module {
        single<TmdbApiDataSource> { TmdbApiDataSourceImpl(get()) }
        single<UserAuthDataSource> { FirebaseUserAuthDataSourceImpl(get()) }
        single<ChatDataSource> { FirebaseChatDataSourceImpl(get()) }
    }

    private val repository = module {
        single<Repository> { RepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<ChatRepository> { ChatRepositoryImpl(get()) }
    }

    private val adapter = module {
        //jika dipanggil harus instance yang baru, bukan single instance
        factory { MovieAdapter()  }
        factory { TvShowAdapter()  }
        factory { CastAdapter()  }
        factory { GenreAdapter()  }
    }

    private val viewModels = module {
        viewModel { HomeListViewModel(get()) }
        viewModel { params -> DetailViewModel(get(),params.get()) }
        viewModelOf(::AuthViewModel)
        viewModelOf(::SplashViewModel)
        viewModelOf(::ChatViewModel)
    }

    private val firebase = module {
        single { FirebaseAuth.getInstance() }
        single { params ->
            GoogleSignIn.getClient(
                params.get(), GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(BuildConfig.FIREBASE_WEB_CLIENT_ID)
                    .requestEmail()
                    .build()
            )
        }
        single { Firebase.database }
    }

}