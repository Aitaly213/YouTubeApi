package com.geektech.youtubeapi.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.geektech.youtubeapi.ui.disconnect.DisconnectActivity


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifi!!.isAvailable || mobile!!.isAvailable) {
            context.startActivity(Intent(context,DisconnectActivity::class.java))
        }
    }

}
