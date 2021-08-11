package com.geektech.youtubeapi.di

import com.geektech.youtubeapi.ui.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}