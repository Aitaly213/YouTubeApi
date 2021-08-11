package com.geektech.youtubeapi.ui.disconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.utils.NetworkCheker
import kotlinx.android.synthetic.main.activity_disconnect.*

class DisconnectActivity : AppCompatActivity() {
    private lateinit var networkCheker: NetworkCheker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disconnect)
        networkCheker = NetworkCheker(this)

        btn_try_again.setOnClickListener {
            if (isNetworkAvaible()) {
                finish()
            } else {
                Toast.makeText(baseContext, "Please, connect to wifi", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun isNetworkAvaible(): Boolean {
        var isAvaible = false
        networkCheker.observe(this, {
            isAvaible = it
        })

        return isAvaible
    }
}
