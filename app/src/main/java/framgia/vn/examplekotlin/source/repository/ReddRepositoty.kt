package framgia.vn.examplekotlin.source.repository

import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.local.ReddLocalDataSource
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import framgia.vn.examplekotlin.source.remote.ReddRemoteDataSource
import rx.Observable

class ReddRepositoty private constructor(private var local: ReddDataSource,private var remote: ReddDataSource) : ReddDataSource {
    companion object {
        private  var instance: ReddRepositoty? = null

        public fun getInstance(local: ReddLocalDataSource, remote: ReddRemoteDataSource): ReddRepositoty {
            if (instance == null)
                instance = ReddRepositoty(local as ReddLocalDataSource, remote as ReddRemoteDataSource)
            return instance as ReddRepositoty
        }
    }

    override fun getAll(): Observable<List<RedditNewsItem>> = remote.getAll()

    override fun getReddById(id: String): Observable<List<RedditNewsItem>> = remote.getReddById(id)


}