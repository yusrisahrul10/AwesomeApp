package engineer.yusrisahrul.awesomeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import engineer.yusrisahrul.awesomeapp.R
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto

class ListPhotoAdapter(
    private val photoDetail: (DataPhoto) -> Unit,
    private val layoutManager: GridLayoutManager? = null
) : PagedListAdapter<DataPhoto, ListPhotoAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.LIST.ordinal -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_photo_list, parent, false))
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_photo_grid, parent, false))
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title: TextView = holder.itemView.findViewById(R.id.tvTitle)
        val pict: ImageView = holder.itemView.findViewById(R.id.imgPoster)

        title.text = getItem(position)?.photographer

        holder.itemView.also {
            Glide.with(it.context)
                    .load(getItem(position)?.src?.medium)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pict)
        }

        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> photoDetail(it1) }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }

    enum class ViewType {
        LIST,
        GRID
    }

    companion object {

        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataPhoto>() {
            override fun areItemsTheSame(oldItem: DataPhoto, newItem: DataPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataPhoto, newItem: DataPhoto): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}