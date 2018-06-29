package framgia.vn.examplekotlin.source.local

import android.content.Context
import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable

class ReddLocalDataSource(private var context: Context) : ReddDataSource {

    override fun getAll(affter: String?, offer: String): Observable<RedditDataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAll(): Observable<List<RedditNewsItem>> {
        return RedditDatabase.getInstance(context).RedditDAO().getAll()
    }

    override fun getReddById(it: String): Observable<List<RedditNewsItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getByNameAuthor(nameAutho: String): Observable<List<RedditNewsItem>> {
        return RedditDatabase.getInstance(context).RedditDAO().getByNameAuthor(nameAutho)
    }

    fun insertReddits(reddits: List<RedditNewsItem>): Boolean {
        return RedditDatabase.getInstance(context).RedditDAO().insertReddits(reddits)
    }

}