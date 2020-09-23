package com.gino.reddittestclient

import android.app.Application
import com.gino.reddittestclient.di.AppModule
import com.gino.reddittestclient.di.DaggerNewsComponent
import com.gino.reddittestclient.di.NewsComponent
import com.gino.reddittestclient.di.NewsModule

/**
 * @author gino.ghiotto
 */
class App : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
            .appModule(AppModule(this))
            .newsModule(NewsModule())
            .build()
    }
}