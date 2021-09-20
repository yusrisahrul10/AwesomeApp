package engineer.yusrisahrul.awesomeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.databinding.AdapterPhotoGridBinding

class GridPhotoAdapter(
        private val photoDetail: (DataPhoto) -> Unit
) : PagedListAdapter<DataPhoto, GridPhotoAdapter.ViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(AdapterPhotoGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = getItem(position)?.photographer

            holder.itemView.also {
                Glide.with(it.context)
                        .load(getItem(position)?.src?.medium)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPoster)
            }

            root.setOnClickListener {
                getItem(position)?.let { it1 -> photoDetail(it1) }
            }
        }    }

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

    class ViewHolder(val view: AdapterPhotoGridBinding) : RecyclerView.ViewHolder(view.root)
}