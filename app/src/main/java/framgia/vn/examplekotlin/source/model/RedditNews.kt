package framgia.vn.examplekotlin.source.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import framgia.vn.examplekotlin.adapter.ViewType
import framgia.vn.examplekotlin.util.TYPE
@Entity(primaryKeys = ["firstName","numComment" ] ,tableName = "reddit" )
data class RedditNewsItem(
        @ColumnInfo(name = "author")
        val author: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "numComment")
        val numComments: Int,
        @ColumnInfo(name = "created")
        val created: Long,
        @ColumnInfo(name = "thumbnail")
        val thumbnail: String,
        @ColumnInfo(name = "url")
        val url: String?
) : ViewType, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun getViewType(): Int = TYPE.NEWS

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RedditNewsItem> {
        override fun createFromParcel(parcel: Parcel): RedditNewsItem {
            return RedditNewsItem(parcel)
        }

        override fun newArray(size: Int): Array<RedditNewsItem?> {
            return arrayOfNulls(size)
        }
    }
}