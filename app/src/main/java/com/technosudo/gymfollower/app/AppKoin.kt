package com.technosudo.gymfollower.app

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppKoin {

    private val environments = module {
        single { AppDatabase.create(androidApplication()) }
    }

    private val viewModels = module {
        //viewModelOf(::)
    }

    private val modules by lazy {
        listOf(
            environments,
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