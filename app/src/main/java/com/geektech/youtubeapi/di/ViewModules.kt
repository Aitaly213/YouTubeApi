package com.geektech.youtubeapi.di

import com.geektech.youtubeapi.ui.detail_playlist.DetailPlayListActivity
import com.geektech.youtubeapi.ui.detail_playlist.DetailViewModel
import com.geektech.youtubeapi.ui.playlist.PlayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules : Module = module {
    viewModel { PlayListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}