package com.gino.reddittestclient.ui.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gino.reddittestclient.R
import com.gino.reddittestclient.utils.*
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * @author gino.ghiotto
 */
class NewsDelegateAdapter(val viewActions: onViewSelectedListener) : ViewTypeDelegateAdapter {

    var selectedPosition: Int = -1


    interface onViewSelectedListener {
        fun onItemSelected(item: RedditNewsItem?)
        fun onItemDismissed(item: RedditNewsItem?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)


    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)
    ) {

        private val imgThumbnail = itemView.img_thumbnail
        private val description = itemView.description
        private val author = itemView.author
        private val comments = itemView.comments
        private val time = itemView.time
        private val dismiss = itemView.dismiss

        fun bind(item: RedditNewsItem) {
            item.thumbnail?.let { imgThumbnail.loadImg(it) }
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()

            description.setTextColor(
                if (item.readed) Color.parseColor("#0aad3f") else Color.parseColor(
                    "#040404"
                )
            )

            super.itemView.setOnClickListener {
                viewActions.onItemSelected(item)
            }

            dismiss.setOnClickListener {
                viewActions.onItemDismissed(item)
            }
        }
    }
}