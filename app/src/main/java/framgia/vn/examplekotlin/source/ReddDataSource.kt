package framgia.vn.examplekotlin.source

import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable
import java.util.*

interface ReddDataSource {
    fun getAll(affter: String?, offer: String): Observable<RedditDataResponse>
    fun getReddById(it: String): Observable<List<RedditNewsItem>>
}