package framgia.vn.examplekotlin.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import framgia.vn.examplekotlin.source.model.RedditNewsItem

@Database(entities = [RedditNewsItem::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun RedditDAO(): RedditDAO

    companion object {
        private var INSTANCE: RedditDatabase? = null
        fun getInstance(context: Context): RedditDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, RedditDatabase::class.java, "room.db")
                        .build()
            }
            return INSTANCE as RedditDatabase
        }
    }
}