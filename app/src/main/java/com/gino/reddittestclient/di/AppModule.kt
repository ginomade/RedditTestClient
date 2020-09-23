package com.gino.reddittestclient.di

import android.app.Application
import android.content.Context
import com.gino.reddittestclient.App
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * @author gino.ghiotto
 */
@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.Default
}