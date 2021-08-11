package com.geektech.youtubeapi.ui.detail_playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.core.network.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.model.PlayListDetail
import com.geektech.youtubeapi.ui.repository.Repository

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun fetchVideoById(id: String): LiveData<Resource<PlayListDetail?>> {

        return repository.fetchYoutubeApiVideo(id)
    }
}