package com.geektech.youtubeapi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import com.bumptech.glide.Glide
import java.net.InetAddress


class Utils {

    companion object {

        fun ImageView.loadImage(context: Context, url: String) {
            Glide.with(context)
                .load(url)
                .into(this)
        }
    }
}