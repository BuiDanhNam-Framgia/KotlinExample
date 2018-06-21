package framgia.vn.examplekotlin.source

import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable
import java.util.*

interface ReddDataSource {
    fun getAll():Observable<List<RedditNewsItem>>
    fun getReddById(it:String) :Observable<List<RedditNewsItem>>
}