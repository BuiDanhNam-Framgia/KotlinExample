package framgia.vn.examplekotlin.screens.main

import framgia.vn.examplekotlin.source.local.ReddLocalDataSource
import framgia.vn.examplekotlin.source.remote.ReddRemoteDataSource
import framgia.vn.examplekotlin.source.repository.ReddRepositoty
import rx.schedulers.Schedulers

class MainPresenter(private var view: Main2Activity) {
    private var respository: ReddRepositoty

    init {
        var local = ReddLocalDataSource()
        var remote = ReddRemoteDataSource()
        respository = ReddRepositoty.getInstance(local, remote)
    }

    fun getNews() {
        respository.getAll()
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