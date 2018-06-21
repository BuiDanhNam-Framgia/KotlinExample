package framgia.vn.examplekotlin.source.remote

import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import rx.Observable

class ReddRemoteDataSource : ReddDataSource {
    override fun getAll(): Observable<List<RedditNewsItem>> = Observable.create({ subscriber ->
        //call API
        subscriber.onNext(fakeData())
        subscriber.onCompleted()
    })

    private fun fakeData(): List<RedditNewsItem> {
        return listOf(
                RedditNewsItem("nam1", "121", 121, 21431, "abc", null),
                RedditNewsItem("nam2", "122", 121, 21431, "abc", null),
                RedditNewsItem("nam3", "123", 121, 21431, "abc", null),
                RedditNewsItem("nam4", "124", 121, 21431, "abc", null)
        )
    }

    override fun getReddById(it: String): Observable<List<RedditNewsItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}