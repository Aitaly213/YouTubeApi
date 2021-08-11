package com.geektech.youtubeapi.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.youtubeapi.BuildConfig.API_KEY
import com.geektech.youtubeapi.core.network.Resource
import com.geektech.youtubeapi.data.remote.ApiService
import com.geektech.youtubeapi.model.PlayList
import com.geektech.youtubeapi.model.PlayListDetail
import com.geektech.youtubeapi.utils.Constant
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService) {


    fun fetchYoutubeApiPlayList(): LiveData<Resource<PlayList?>> = liveData(Dispatchers.IO) {

        emit(Resource.loading(null))

        val response = apiService.fetchAllPlayList(API_KEY, Constant.PART, Constant.CHANNEL_ID)
        emit(
            if (response.isSuccessful) Resource.success(response.body(), response.code())
            else Resource.error(response.message(), response.body(), response.code())
        )
    }

    fun fetchYoutubeApiVideo(id: String): LiveData<Resource<PlayListDetail?>> =
        liveData(Dispatchers.IO) {

            emit(Resource.loading(null))

            val response = apiService.getVideoListFromPlaylist(Constant.PART, id, API_KEY, 10)
            emit(
                if (response.isSuccessful) Resource.success(response.body(), response.code())
                else Resource.error(response.message(), response.body(), response.code())
            )
        }

}