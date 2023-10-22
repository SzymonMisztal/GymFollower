package com.technosudo.gymfollower.app

import android.content.Context
import com.technosudo.gymfollower.ui.screens.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppKoin {

    private val environments = module {
        single { AppDatabase.create(androidApplication()) }
    }

    private val dataSources = module {
    }

    private val repositories = module {
    }

    private val viewModels = module {
        viewModelOf(::MainViewModel)
    }

    private val misc = module {
    }

    private val modules by lazy {
        listOf(
            environments,
            dataSources,
            repositories,
            viewModels,
            misc
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