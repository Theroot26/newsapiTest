import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.appnews.R
import com.test.appnews.models.newsmodels.Article

class MyAdapter(private val context: Context,
                private val items: List<Article>,
                private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>()  {

    // ViewHolder class for caching the views in each list item
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val titleTextView2: TextView = itemView.findViewById(R.id.contentTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items_layout, parent, false)
        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]


        if(item.title.length < 80){

            holder.titleTextView.text = item.title

        }else{
            holder.titleTextView.text = "${item.title.substring(0,80)}..."
        }

        holder.titleTextView2.text = item.description

//        if(item.description.length < 150){
//            holder.titleTextView2.text = item.description
//
//        }else{
//
//            holder.titleTextView2.text = "${item.description.substring(0,150)}..."
//        }



        println("image url"+item.urlToImage)

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(item.urlToImage)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher) // Placeholder image while loading
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return items.size
    }
}
