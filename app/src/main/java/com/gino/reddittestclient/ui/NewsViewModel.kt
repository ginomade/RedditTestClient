package com.gino.reddittestclient.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gino.reddittestclient.data.NewsAPI
import com.gino.reddittestclient.data.RedditNewsResponse
import com.gino.reddittestclient.utils.RedditNews
import com.gino.reddittestclient.utils.RedditNewsItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author gino.ghiotto
 */

class NewsViewModel @Inject constructor(
    private val api: NewsAPI,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    val newsState: MutableLiveData<NewsState> = MutableLiveData()

    fun fetchNews(after: String, limit: String = "10") = GlobalScope.launch(coroutineContext) {
        try {
            val result = api.getNews(after, limit)
            val news = process(result)
            newsState.postValue(NewsState.Success(news))
        } catch (e: Throwable) {
            newsState.postValue(NewsState.Error(e.message))
        }
    }

    private fun process(response: RedditNewsResponse): RedditNews {
        val dataResponse = response.data
        val news = dataResponse.children.map {
            val item = it.data
            RedditNewsItem(
                item.author, item.title, item.num_comments,
                item.created, item.thumbnail, item.url, false
            )
        } as ArrayList
        return RedditNews(
            dataResponse.after.orEmpty(),
            dataResponse.before.orEmpty(),
            news
        )
    }


}