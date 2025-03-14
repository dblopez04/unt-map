package com.example.dpmap

import android.app.Application
import com.example.dpmap.data.AppContainer
import com.example.dpmap.data.DefaultAppContainer

class DPMapApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}