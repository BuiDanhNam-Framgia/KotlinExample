package framgia.vn.examplekotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import framgia.vn.examplekotlin.R
import framgia.vn.examplekotlin.inflate
import framgia.vn.examplekotlin.loadImage
import framgia.vn.examplekotlin.source.model.RedditNewsItem
import kotlinx.android.synthetic.main.item_listview.view.*

class RedditNewAdapter(private val listener: Listtener) : RecyclerView.Adapter<RedditNewAdapter.ViewHolder>() {
    //    ? marks a type as nullable
    private var items: ArrayList<RedditNewsItem>

    init {
        this.items = ArrayList()
    }

    constructor(item: ArrayList<RedditNewsItem>, listener: Listtener) : this(listener) {
        this.items = item
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


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun binder(data: RedditNewsItem) {
            // data?.outhor as if(data != null ) {data.author}
            itemView.author.text = data.author
            itemView.description.text = data.title
            itemView.comments.text = data.numComments.toString()
            itemView.time.text = data.created.toString()
            itemView.img_thumbnail.loadImage(data.thumbnail)
            itemView.setOnClickListener { listener.onItemSelect(data) }
        }
    }

}

interface Listtener {
    fun onItemSelect(item: RedditNewsItem)
}