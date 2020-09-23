package com.gino.reddittestclient.data

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author gino.ghiotto
 */
interface  RedditApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>

    @GET("/top.json")
    suspend fun getDeferredTop(@Query("after") after: String,
                       @Query("limit") limit: String): RedditNewsResponse
}