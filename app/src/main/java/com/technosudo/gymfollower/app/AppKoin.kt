package com.technosudo.gymfollower.app

import android.content.Context
import com.technosudo.gymfollower.domain.repository.ExerciseRepository
import com.technosudo.gymfollower.domain.repository.ExerciseRepositoryImpl
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import com.technosudo.gymfollower.domain.repository.ProgressRepositoryImpl
import com.technosudo.gymfollower.ui.screens.invidualprogress.IndividualProgressViewModel
import com.technosudo.gymfollower.ui.screens.main.MainViewModel
import com.technosudo.gymfollower.ui.screens.progress.ProgressViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object AppKoin {

    private val environments = module {
        single { AppDatabase.create(androidApplication()) }
    }

    private val dataSources = module {
        single { get<AppDatabase>().exerciseDao() }
        single { get<AppDatabase>().progressDao() }
    }

    private val repositories = module {
        singleOf(::ExerciseRepositoryImpl) bind ExerciseRepository::class
        singleOf(::ProgressRepositoryImpl) bind ProgressRepository::class
    }

    private val viewModels = module {
        viewModelOf(::MainViewModel)
        viewModelOf(::ProgressViewModel)
        viewModelOf(::IndividualProgressViewModel)
    }

    private val modules by lazy {
        listOf(
            environments,
            dataSources,
            repositories,
            viewModels
        )
    }

    fun init(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(modules)
        }
    }
}