package com.geektech.youtubeapi.di

import com.geektech.youtubeapi.data.remote.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules

)