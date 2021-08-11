package com.geektech.youtubeapi.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        showDisconnectState()
        setupUI()
        setupLiveData()
    }

    abstract fun showDisconnectState()

    abstract fun setupUI()

    abstract fun setupLiveData()

}