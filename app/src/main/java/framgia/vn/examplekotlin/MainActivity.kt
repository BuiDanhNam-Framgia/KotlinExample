package framgia.vn.examplekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_listview.view.*
import java.util.*

class MainActivity : AppCompatActivity(), onViewSelect {

    override fun onItemClick(dat: String) {
        Toast.makeText(this, dat, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
//        var adapter = CusAdapter(arrayListOf("1","2","3"),this@MainActivity)
//        or
        var adapter = CusAdapter(arrayListOf("1", "2", "3"), this)
        news_list?.layoutManager = LinearLayoutManager(this)
        news_list?.adapter = adapter

//        var ts = this@MainActivity.find<>()\

//        listView = findViewById<RecyclerView>(R.id.news_list).apply {
//            this.adapter = adapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//
//        }
    }

    inner class CusAdapter(private val data: ArrayList<String>, private val listener: onViewSelect) : RecyclerView.Adapter<CusAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
//             or
            return ViewHolder(parent.inflate(R.layout.item_listview))
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(data[position])
        }

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            var tv = itemView.tv

            fun bind(data: String) {
                itemView.apply {
                    tv.text = data
                    setOnClickListener { listener.onItemClick(data) }
                }

            }
        }
//        inner class ViewHolder2(parent: ViewGroup):RecyclerView.ViewHolder(parent){
//            private val tv = itemView.tv
//            init {
//
//            }
//            fun bin(data:String){
//                tv.setText(data)
//                itemView.setOnClickListener(view)
//            }
//        }
    }

}

interface onViewSelect {
    fun onItemClick(dat: String)
}
