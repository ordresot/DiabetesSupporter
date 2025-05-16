package com.ordresot.diabetessupporter.presentation

import android.app.Application
import com.ordresot.diabetessupporter.core.di.Creator

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Creator.initContext(this)
    }
}