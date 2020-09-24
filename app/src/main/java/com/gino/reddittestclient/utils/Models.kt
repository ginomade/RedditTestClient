package com.gino.reddittestclient.utils

import android.os.Build.VERSION_CODES.Q
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

/**
 * @author gino.ghiotto
 */
data class RedditNews(
    val after: String?,
    val before: String?,
    val news: ArrayList<RedditNewsItem>
) : Parcelable {
    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNews> = object : Parcelable.Creator<RedditNews> {
            override fun createFromParcel(source: Parcel): RedditNews = RedditNews(source)
            override fun newArray(size: Int): Array<RedditNews?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel?) : this(
        source?.readString(), source?.readString(),
        source?.createTypedArrayList(RedditNewsItem.CREATOR) as ArrayList<RedditNewsItem>
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(after)
        dest?.writeString(before)
        dest?.writeTypedList(news)
    }
}

data class RedditNewsItem(
    val author: String?,
    val title: String?,
    val numComments: Int,
    val created: Long,
    val thumbnail: String?,
    val url: String?,
    var readed: Boolean

) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.NEWS

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNewsItem> =
            object : Parcelable.Creator<RedditNewsItem> {
                @RequiresApi(Q)
                override fun createFromParcel(source: Parcel): RedditNewsItem =
                    RedditNewsItem(source)

                override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
            }
    }

    @RequiresApi(Q)
    constructor(source: Parcel) : this(
        source.readString(), source.readString(), source.readInt(),
        source.readLong(), source.readString(), source.readString(), source.readBoolean()
    )

    override fun describeContents() = 0

    @RequiresApi(Q)
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
        dest?.writeBoolean(readed)
    }
}