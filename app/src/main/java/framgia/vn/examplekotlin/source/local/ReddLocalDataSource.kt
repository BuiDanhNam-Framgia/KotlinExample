package framgia.vn.examplekotlin.source.local

import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable

class ReddLocalDataSource:ReddDataSource{

    override fun getAll(affter:String? , offer:String): Observable<RedditDataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReddById(it: String): Observable<List<RedditNewsItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}