package com.gino.reddittestclient.data

import retrofit2.Call
import javax.inject.Inject

/**
 * @author gino.ghiotto
 */
class NewsRestAPI @Inject constructor(private val redditApi: RedditApi) : NewsAPI {

    override suspend fun getNews(after: String, limit: String): RedditNewsResponse {
        return redditApi.getDeferredTop(after, limit)
    }

    override fun getNewsOldApi(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}