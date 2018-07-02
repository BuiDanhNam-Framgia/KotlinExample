package framgia.vn.examplekotlin.source.repository

import android.content.Context
import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.local.ReddLocalDataSource
import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import framgia.vn.examplekotlin.source.model.RedditNewsItemResponse
import framgia.vn.examplekotlin.source.remote.ReddRemoteDataSource
import framgia.vn.examplekotlin.util.checkInternet
import rx.Observable

class ReddRepositoty private constructor(private var local: ReddDataSource, private var remote: ReddDataSource) : ReddDataSource {
    private lateinit var context: Context

    private constructor(local: ReddDataSource, remote: ReddDataSource, context: Context) : this(local, remote) {
        this.context = context
    }

    companion object {
        private var instance: ReddRepositoty? = null

        fun getInstance(local: ReddLocalDataSource, remote: ReddRemoteDataSource): ReddRepositoty {
            if (instance == null)
                instance = ReddRepositoty(local, remote)
            return instance as ReddRepositoty
        }

        fun getInstance(local: ReddLocalDataSource, remote: ReddRemoteDataSource, context: Context): ReddRepositoty {
            if (instance == null)
                instance = ReddRepositoty(local, remote, context)
            return instance as ReddRepositoty
        }


    }

    override fun getAll(affter: String?, offer: String): Observable<RedditDataResponse> {
        if (checkInternet(context)) {
            remote.getAll(affter, offer).subscribe({ it ->
                var listdata: MutableList<RedditNewsItem> = mutableListOf()
                it.children.forEach { listdata.add(it.data) }
                (local as ReddLocalDataSource).insertReddits(listdata)
            }
            )
            return remote.getAll(affter, offer)
        } else {
            var list: MutableList<RedditNewsItemResponse> = mutableListOf()
            (local as ReddLocalDataSource).getAll().subscribe({ it ->
                it.forEach {
                    var redditNewsItem = RedditNewsItemResponse(it)
                    list.add(redditNewsItem)
                }

            })
            return Observable.create<RedditDataResponse> { it ->
                var returl = RedditDataResponse(list, null, null)
                it.onNext(returl)
                it.onCompleted()
            }
        }

    }

    override fun getReddById(id: String): Observable<List<RedditNewsItem>> = remote.getReddById(id)

    fun getLocal(): ReddLocalDataSource {
        return local as ReddLocalDataSource
    }

}