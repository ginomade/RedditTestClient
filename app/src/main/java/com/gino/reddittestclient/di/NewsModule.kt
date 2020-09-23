package com.gino.reddittestclient.di

import com.gino.reddittestclient.data.NewsAPI
import com.gino.reddittestclient.data.NewsRestAPI
import com.gino.reddittestclient.data.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author gino.ghiotto
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditApi): NewsAPI = NewsRestAPI(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

}