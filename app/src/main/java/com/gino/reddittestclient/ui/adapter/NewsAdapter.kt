package com.gino.reddittestclient.ui.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.gino.reddittestclient.utils.AdapterConstants
import com.gino.reddittestclient.utils.RedditNewsItem
import com.gino.reddittestclient.utils.ViewType
import com.gino.reddittestclient.utils.ViewTypeDelegateAdapter
import java.util.*

/**
 * @author gino.ghiotto
 */
class NewsAdapter(listener: NewsDelegateAdapter.onViewSelectedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addNews(news: List<RedditNewsItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditNewsItem> =
        items
            .filter { it.getViewType() == AdapterConstants.NEWS }
            .map { it as RedditNewsItem }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    fun itemReaded(item: RedditNewsItem) {
        for (newsItem in items) {
            if (newsItem is RedditNewsItem) {
                if (newsItem as RedditNewsItem == item) {
                    items.remove(newsItem)
                }
            }
        }
        notifyDataSetChanged()
    }
}