package framgia.vn.examplekotlin.screens.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import framgia.vn.examplekotlin.R
import framgia.vn.examplekotlin.adapter.Listtener2
import framgia.vn.examplekotlin.adapter.RedditNewAdapter
import framgia.vn.examplekotlin.source.local.ReddLocalDataSource
import framgia.vn.examplekotlin.source.model.RedditDataResponse
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import framgia.vn.examplekotlin.source.remote.ReddRemoteDataSource
import framgia.vn.examplekotlin.source.repository.ReddRepositoty
import framgia.vn.examplekotlin.util.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), Listtener2 {

    override fun onImgageSelect(item: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelect(item: RedditNewsItem?) {

    }

    private var presenter: MainPresenter
    private lateinit var adapter: RedditNewAdapter
    private var affterPager: String? = null
    private val offer: String = "10"

    init {
        var local = ReddLocalDataSource(this)
        var remote = ReddRemoteDataSource()
        var respository = ReddRepositoty.getInstance(local, remote, this)
        presenter = MainPresenter(this, respository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        adapter = RedditNewAdapter()
        //lambas
        adapter.setClickListener(this)
        var layoutManager = LinearLayoutManager(this)
//        adapter.setClickListener(object : Listtener2 {
//            override fun onItemSelect(item: RedditNewsItem) {
//
//            }
//
//            override fun onItemSelect2(item: Int) {
//
//            }
//        })

        adapter = RedditNewAdapter()
        lv.apply {
            this.layoutManager = layoutManager
            this.addOnScrollListener(EndlessScrollListener({ onLoadMore() }, layoutManager))

        }
        lv.adapter = adapter
        refresh_lv.setOnRefreshListener { onRefresh() }
        initData()
    }

    private fun onRefresh() {
        affterPager = null
        getNews(affterPager, offer, true)
    }

    private fun onLoadMore() {
        getNews(affterPager, offer, false)
    }

    private fun initData() {
        getNews(affterPager, offer, false)
    }

    private fun getNews(affterPager: String?, offer: String, refresh: Boolean) {
        if (!refresh) {
            pr_load.visibility = View.VISIBLE
        }
        presenter.getNews(affterPager, offer)
    }

    fun onLoadSuccess(listNews: RedditDataResponse) {
        var listdata: MutableList<RedditNewsItem> = mutableListOf()
        listNews.children.forEach { listdata.add(it.data) }
        affterPager = listNews.after
        adapter.addNews(listdata)
    }

    fun onLoadSuccess(listNews: List<RedditNewsItem>) {
        adapter.addNews(listNews)
    }

    fun onLoadCommplete() {
        pr_load.visibility = View.GONE
        refresh_lv.isRefreshing = false
    }

    fun onLoadError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

}
