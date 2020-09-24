package com.gino.reddittestclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gino.reddittestclient.App
import com.gino.reddittestclient.R
import com.gino.reddittestclient.ui.adapter.NewsAdapter
import com.gino.reddittestclient.ui.adapter.NewsDelegateAdapter
import com.gino.reddittestclient.utils.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

/**
 * @author gino.ghiotto
 */
class NewsFragment() : Fragment(), NewsDelegateAdapter.onViewSelectedListener {

    override fun onItemSelected(item: RedditNewsItem?) {
        item?.let { myListener.elementSelected(item) }
    }

    override fun onItemDismissed(item: RedditNewsItem?) {
        item?.let { itemReaded(it) }
    }

    lateinit var myListener: ElementSelectedListener

    fun setListener(listener: ElementSelectedListener) {
        myListener = listener
    }

    companion object {
        private const val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null
    private val newsAdapter by androidLazy { NewsAdapter(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsViewModel>
    private val newsViewModel by androidLazy {
        getViewModel<NewsViewModel>(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.newsComponent.inject(this)

        newsViewModel.newsState.observe(this, Observer<NewsState> {
            manageState(it)
        })

    }

    private fun manageState(kedditState: NewsState?) {
        val state = kedditState ?: return
        when (state) {
            is NewsState.Success -> {
                redditNews = state.redditNews
                state.redditNews.news?.let { newsAdapter.addNews(it) }
            }
            is NewsState.Error -> {
                Snackbar.make(news_list, state.message.orEmpty(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY") { requestNews() }
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        news_list.adapter = newsAdapter

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            redditNews!!.news?.let { newsAdapter.clearAndAddNews(it) }
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = newsAdapter.getNews() as ArrayList
        if (redditNews != null && news.isNotEmpty()) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        newsViewModel.fetchNews(redditNews?.after.orEmpty())
    }

    interface ElementSelectedListener {
        fun elementSelected(item: RedditNewsItem)
    }

    fun itemReaded(item: RedditNewsItem) {
        val news: ArrayList<RedditNewsItem> = ArrayList()
        news.addAll(newsAdapter.getNews() as ArrayList)

        if (news.contains(item)) {
            news.remove(item)
        }

        news_list.adapter = newsAdapter
        newsAdapter.clearAndAddNews(news)
    }
}