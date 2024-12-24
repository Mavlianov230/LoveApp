package com.example.loveapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoveApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}