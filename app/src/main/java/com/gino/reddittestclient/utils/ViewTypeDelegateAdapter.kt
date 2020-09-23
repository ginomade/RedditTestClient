package com.gino.reddittestclient.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author gino.ghiotto
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}