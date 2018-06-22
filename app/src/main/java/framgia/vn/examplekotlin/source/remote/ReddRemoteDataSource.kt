package framgia.vn.examplekotlin.source.remote

import framgia.vn.examplekotlin.api.RetrofitClient
import framgia.vn.examplekotlin.api.url
import framgia.vn.examplekotlin.source.ReddDataSource
import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import framgia.vn.examplekotlin.source.model.RedditNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable

class ReddRemoteDataSource : ReddDataSource {
    override fun getAll(affter:String? , offer:String): Observable<RedditDataResponse> = Observable.create(
            { subscriber ->
                //call API
                RetrofitClient.getRetrofitAPI(url)?.getTop(affter ,offer)?.enqueue(object : Callback<RedditNewsResponse> {
                    override fun onFailure(call: Call<RedditNewsResponse>?, t: Throwable?) {
                        subscriber.onError(t)
                    }

                    override fun onResponse(call: Call<RedditNewsResponse>?, response: Response<RedditNewsResponse>?) {
                        if (response?.isSuccessful!!) {
                            var list= response.body()?.data
//
                            subscriber.onNext(list)
                            subscriber.onCompleted()
                        }
                    }
                })
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