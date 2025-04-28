package com.ordresot.diabetessupporter.presentation

import android.app.Application
import com.redikt.diabetesapp.core.di.Creator

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Creator.initContext(this)
    }
}