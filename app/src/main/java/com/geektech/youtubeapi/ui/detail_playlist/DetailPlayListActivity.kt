package com.geektech.youtubeapi.ui.detail_playlist

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.core.network.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.ui.disconnect.DisconnectActivity
import com.geektech.youtubeapi.utils.NetworkCheker
import kotlinx.android.synthetic.main.activity_detail_play_list.*

class DetailPlayListActivity : BaseActivity(R.layout.activity_detail_play_list) {

    private val detailViewModel: DetailViewModel by viewModel()

    private val adapter by lazy { DetailPlayListAdapter(this::clickListener) }

    private lateinit var networkCheker: NetworkCheker


    override fun showDisconnectState() {
        networkCheker = NetworkCheker(this)
        if (!networkCheker.isNetworkAvaible(this)) {
            startActivity(Intent(this, DisconnectActivity::class.java))
        }
        networkCheker.observe(this, {
            if (it) {
                setupLiveData()
            } else {
                startActivity(Intent(this, DisconnectActivity::class.java))
            }
        })
    }

    override fun setupUI() {
        video_list_recycler.adapter = adapter

    }

    override fun setupLiveData() {
        detailViewModel.loading.observe(this, {
            if (it) pb_detail_playlist.visibility = View.VISIBLE
            else pb_detail_playlist.visibility = View.GONE
        })

        val id = intent.getStringExtra("key_id") ?: "not"
        detailViewModel.fetchVideoById(id).observe(this, { response ->

            when (response.status) {

                Status.LOADING -> detailViewModel.loading.postValue(true)

                Status.ERROR -> {
                    detailViewModel.loading.postValue(false)
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    detailViewModel.loading.postValue(false)
                    if (response?.data != null) {
                        response.data.items.let { adapter.addItems(it) }
                    }
                }
            }
        })
        getData()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val title = intent.getStringExtra("key_title") ?: "not"
//        val description = intent.getStringExtra("key_description") ?: "not"
        val itemCount = intent.getIntExtra("key_item_count",0)

        tv_detail_playlist_title.text = title
//        tv_detail_playlist_subtitle.text = description
        tv_video_series.text = "${itemCount}  ${getString(R.string.video_series)}"
    }


    private fun clickListener(id: String) {
        val intent = Intent(this, DetailPlayListActivity::class.java)
        intent.putExtra("Key", id)
        startActivity(intent)
    }

}
