package com.app.memo

import android.app.Application
import android.content.Context
import com.app.memo.di.AppComponent
import com.app.memo.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }