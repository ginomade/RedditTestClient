package com.gino.reddittestclient.data

import kotlinx.coroutines.Deferred
import retrofit2.Call

/**
 * @author gino.ghiotto
 */
interface NewsAPI {
    fun getNewsOldApi(after: String, limit: String): Call<RedditNewsResponse>
    suspend fun getNews(after: String, limit: String): RedditNewsResponse
}