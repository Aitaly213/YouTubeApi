package com.geektech.youtubeapi.ui.playlist


import android.content.Intent
import android.view.View
import android.widget.Toast
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.core.network.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.ui.detail_playlist.DetailPlayListActivity
import com.geektech.youtubeapi.ui.disconnect.DisconnectActivity
import com.geektech.youtubeapi.utils.NetworkCheker
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: PlayListViewModel by viewModel()

    private val adapter by lazy { PlayListAdapter(this::clickListener) }

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
        rv_playLists.adapter = adapter
    }

    override fun setupLiveData() {

        viewModel.loading.observe(this, {
            if (it) pb_playlist.visibility = View.VISIBLE
            else pb_playlist.visibility = View.GONE
        })

        viewModel.fetchPlayList().observe(this, { response ->

            when (response.status) {

                Status.LOADING -> viewModel.loading.postValue(true)

                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    if (response?.data != null) {
                        response.data.items?.let { adapter.addItems(it) }
                    }
                }
            }
        })
    }

    private fun clickListener(id: String, title: String, itemCount: Int) {
        val intent = Intent(this, DetailPlayListActivity::class.java)
        intent.putExtra("key_id", id)
        intent.putExtra("key_title",title)
        intent.putExtra("key_item_count",itemCount)
        startActivity(intent)
    }

}