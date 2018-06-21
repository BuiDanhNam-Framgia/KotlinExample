package framgia.vn.examplekotlin.screens.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import framgia.vn.examplekotlin.R
import framgia.vn.examplekotlin.adapter.Listtener2
import framgia.vn.examplekotlin.adapter.RedditNewAdapter
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), Listtener2 {
    override fun onItemSelect2(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

        lv.setOnClickListener{v -> {
             // lambdas
        } }

//        or
        lv.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })
        adapter = RedditNewAdapter()
//        adapter.setClickListener (object : Listtener2{
//            override fun onItemSelect(item: RedditNewsItem) {
//
//            }
//
//        })
        //lambas
        adapter.setClickListener(object :Listtener2{
            override fun onItemSelect(item: RedditNewsItem) {

            }

            override fun onItemSelect2(item: Int) {

            }
        })

        lv.apply {
            layoutManager = LinearLayoutManager(context)
        }
        lv.adapter = adapter
        initData()

    }

    private fun initData() {
        var listRedd: List<RedditNewsItem>? = null
//        lambdas
//        listRedd?.forEach { x -> }
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
fun containsEven(collection: Collection<Int>): Boolean = collection.any { s -> s % 2 ==0  }
