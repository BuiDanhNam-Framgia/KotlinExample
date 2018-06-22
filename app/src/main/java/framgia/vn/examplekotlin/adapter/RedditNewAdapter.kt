package framgia.vn.examplekotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import framgia.vn.examplekotlin.R
import framgia.vn.examplekotlin.inflate
import framgia.vn.examplekotlin.loadImage
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import kotlinx.android.synthetic.main.item_listview.view.*

class RedditNewAdapter() : RecyclerView.Adapter<RedditNewAdapter.ViewHolder>() {
    //    ? marks a type as nullable
    private var items: ArrayList<RedditNewsItem>
    private var listener: Listtener2? = null

    public fun setClickListener(ls: Listtener2){
        this.listener = ls
    }

    public fun setListener(ls:Listtener2){
        listener = ls
    }
    init {
        this.items = ArrayList()
    }

    constructor(item: ArrayList<RedditNewsItem>, listener: Listtener2) : this() {
        this.items = item
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = parent.inflate(R.layout.item_listview)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(this.items.get(position))
    }

    public fun clearAndAddNews(redditNewsItem: List<RedditNewsItem>){
        items.clear()
        items.addAll(redditNewsItem)
        notifyDataSetChanged()
    }
    public fun addNews(redditNewsItem: List<RedditNewsItem>){
        var index = items.size
        items.addAll(redditNewsItem)
        notifyItemRangeChanged(index, items.size)
    }
    public fun refreshNews(redditNewsItem: List<RedditNewsItem>){
        items.clear()
        items.addAll(redditNewsItem)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun binder(data: RedditNewsItem?) {
            // data?.outhor as if(data != null ) {data.author}
            itemView.author.text = data?.author
            itemView.description.text = data?.title
            itemView.comments.text = data?.numComments.toString()
            itemView.time.text = data?.created.toString()
            itemView.img_thumbnail.loadImage(data?.thumbnail)
            itemView.setOnClickListener { listener?.onItemSelect(data) }
        }
    }

}


interface Listtener2 {
    fun onItemSelect(item: RedditNewsItem?)
    fun onImgageSelect(item: String)
}

sealed class Listener
data class OnClickImage(val item:RedditNewsItem) :Listener()
data class OnClickItem(val item:RedditNewsItem) :Listener()
data class OnCLickDescription(val value:String):Listener()