package framgia.vn.examplekotlin.screens.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import framgia.vn.examplekotlin.R
import framgia.vn.examplekotlin.adapter.Listtener
import framgia.vn.examplekotlin.adapter.RedditNewAdapter
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), Listtener {
    override fun onItemSelect(item: RedditNewsItem) {

    }
    private var presenter:MainPresenter
    private lateinit var adapter: RedditNewAdapter

    init {
        presenter  = MainPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        adapter = RedditNewAdapter(this)
        lv.apply {
            layoutManager = LinearLayoutManager(context)
        }
        lv.adapter = adapter
        initData()

    }

    private fun initData() {
        var listRedd: List<RedditNewsItem>
        presenter.getNews()
    }

    fun onLoadSuccess(listNews:List<RedditNewsItem>){
        adapter.addNews(listNews)
    }
    fun onLoadCommplete(){
        pr_load.visibility = View.GONE
    }
    fun onLoadError(error:String?){
        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }
}