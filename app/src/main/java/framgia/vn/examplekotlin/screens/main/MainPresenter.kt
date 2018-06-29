package framgia.vn.examplekotlin.screens.main

import framgia.vn.examplekotlin.source.repository.ReddRepositoty
import rx.schedulers.Schedulers

class MainPresenter(private var view: Main2Activity, private var respository: ReddRepositoty) {


    fun getNews(affterPager: String?, offer: String) {
        respository.getAll(affterPager,offer)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            view.onLoadSuccess(it)
                        },
                        {
                            view.onLoadError(it.message)
                        },
                        {
                            view.onLoadCommplete()
                        })
    }
}