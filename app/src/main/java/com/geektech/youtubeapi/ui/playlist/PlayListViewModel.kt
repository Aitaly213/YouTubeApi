package com.geektech.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.core.network.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.model.PlayList
import com.geektech.youtubeapi.ui.repository.Repository

class PlayListViewModel(private val repository: Repository) : BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun fetchPlayList(): LiveData<Resource<PlayList?>> {
        return repository.fetchYoutubeApiPlayList()
    }

}