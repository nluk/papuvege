package pl.nluk.papuvege.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pl.nluk.papuvege.R
import pl.nluk.papuvege.databinding.ItemCategoryBinding
import pl.nluk.papuvege.databinding.ItemMenuBinding
import pl.nluk.papuvege.models.MenuDisplay
import kotlin.reflect.KFunction1

typealias ViewClicked = (View,String)->Unit

class ImageAdapter(val uris : List<String>, val viewClicked : ViewClicked) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_thumbnail, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(uris[position])

    override fun getItemCount(): Int = uris.size

    inner class ViewHolder(private val root : View) : RecyclerView.ViewHolder(root){

        val onClickListener : View.OnClickListener = View.OnClickListener {
            viewClicked(it, uris[adapterPosition])
        }

        init {
            root.setOnClickListener(onClickListener)
        }

        fun bind(url: String) {
            Glide.with(root)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(root as ImageView)
        }
    }

}