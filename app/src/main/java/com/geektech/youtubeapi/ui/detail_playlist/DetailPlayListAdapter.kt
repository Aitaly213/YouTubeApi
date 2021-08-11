package com.geektech.youtubeapi.ui.detail_playlist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.model.Item
import com.geektech.youtubeapi.utils.Utils.Companion.loadImage
import kotlinx.android.synthetic.main.item_videos.view.*

class DetailPlayListAdapter(private val clickListener: (item: String) -> Unit) :
    RecyclerView.Adapter<DetailPlayListAdapter.ViewHolder>() {

    private var list: List<Item> = listOf()

    fun addItems(playList: List<Item>) {
        list = playList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_videos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            clickListener(list[position].id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun onBind(get: Item) {

            itemView.iv_item_video.loadImage(
                itemView.iv_item_video.context,
                get.snippet.thumbnails.medium.url
            )
            itemView.tv_vid_item_title.text = get.snippet.title

        }


    }

}
