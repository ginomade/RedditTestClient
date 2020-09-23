package com.gino.reddittestclient.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gino.reddittestclient.R
import com.gino.reddittestclient.utils.ViewType
import com.gino.reddittestclient.utils.ViewTypeDelegateAdapter
import com.gino.reddittestclient.utils.inflate

/**
 * @author gino.ghiotto
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading))
}