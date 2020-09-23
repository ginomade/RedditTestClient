package com.gino.reddittestclient.di

import com.gino.reddittestclient.ui.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author gino.ghiotto
 */
@Singleton
@Component(modules = [AppModule::class, NewsModule::class, NetworkModule::class])
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}