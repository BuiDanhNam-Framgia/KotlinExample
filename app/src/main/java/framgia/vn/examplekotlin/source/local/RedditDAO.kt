package framgia.vn.examplekotlin.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable

@Dao
interface RedditDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReddits(reddit: List<RedditNewsItem>): Boolean

    @Query("Select author ,numComment  from reddit ")
    fun getAll(): Observable<List<RedditNewsItem>>

    @Query(value = "Select * from reddit where author = :nameAuthor ")
    fun getByNameAuthor(nameAuthor: String): Observable<List<RedditNewsItem>>

    @Query("Select author , numComment from reddit where author = :author AND title = :title")
    fun getbyNameAuthorAndTitle(author: String, title: String): Observable<List<RedditNewsItem>>
}