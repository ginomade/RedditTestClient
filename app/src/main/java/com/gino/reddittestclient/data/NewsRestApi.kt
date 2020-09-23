package com.gino.reddittestclient.data

import com.gino.reddittestclient.utils.Logger.dt
import kotlinx.coroutines.Deferred
import retrofit2.Call
import java.util.logging.Logger
import javax.inject.Inject

/**
 * @author gino.ghiotto
 */
class NewsRestAPI @Inject constructor(private val redditApi: RedditApi) : NewsAPI {

    override suspend fun getNews(after: String, limit: String): RedditNewsResponse {
        //Logger.dt("calling Rest API")
        return redditApi.getDeferredTop(after, limit)
    }

    override fun getNewsOldApi(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}