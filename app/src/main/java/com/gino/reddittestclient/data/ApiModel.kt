package com.gino.reddittestclient.data

import android.os.Parcel
import android.os.Parcelable

/**
 * @author gino.ghiotto
 */
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
    val author: String?,
    val title: String?,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeInt(num_comments)
        parcel.writeLong(created)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RedditNewsDataResponse> {
        override fun createFromParcel(parcel: Parcel): RedditNewsDataResponse {
            return RedditNewsDataResponse(parcel)
        }

        override fun newArray(size: Int): Array<RedditNewsDataResponse?> {
            return arrayOfNulls(size)
        }
    }
}