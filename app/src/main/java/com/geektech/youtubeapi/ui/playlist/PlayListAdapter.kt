package com.geektech.youtubeapi.ui.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.model.Items
import com.geektech.youtubeapi.utils.Utils.Companion.loadImage
import kotlinx.android.synthetic.main.item_playlist.view.*
import kotlin.reflect.KFunction3

class PlayListAdapter(private val clickListener: KFunction3<String, String, Int, Unit>) :
    RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    private var list: List<Items> = listOf()

    private var context: Context? = null

    fun addItems(playList: List<Items>) {
        list = playList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false),
            context as Context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            clickListener(
                list[position].id,
                list[position].snippet.title,
                list[position].contentDetails.itemCount
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(item: View, private val context: Context) : RecyclerView.ViewHolder(item) {

        @SuppressLint("SetTextI18n")
        fun onBind(get: Items) {

            itemView.iv_item.loadImage(itemView.iv_item.context, get.snippet.thumbnails.medium.url)
            itemView.tv_item_title.text = get.snippet.title
            itemView.video_series.text =
                "${get.contentDetails.itemCount}  ${context.getString(R.string.video_series)}"
        }


    }

}