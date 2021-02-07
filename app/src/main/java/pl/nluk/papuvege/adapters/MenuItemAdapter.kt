package pl.nluk.papuvege.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pl.nluk.papuvege.R
import pl.nluk.papuvege.databinding.ItemCategoryBinding
import pl.nluk.papuvege.databinding.ItemMenuBinding
import pl.nluk.papuvege.models.MenuDisplay
import kotlin.reflect.KFunction1

class MenuItemAdapter(var items : List<MenuDisplay>) : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val (binding, setter) = when(viewType){
            MenuDisplay.ITEM -> (DataBindingUtil.inflate(inflater, R.layout.item_menu, parent, false) as ItemMenuBinding).let { it to it::setItem }
            else -> (DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false) as ItemCategoryBinding).let { it to it::setCategory }
        }
        return ViewHolder(binding, setter)
    }

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ViewDataBinding, private val setter: KFunction1<*, Unit>) : RecyclerView.ViewHolder(binding.root){

        init {
            Log.i("VH_MENU", "Constructed: $this")
        }

        fun bind(item: MenuDisplay) {
            Log.i("VH_MENU", "Binding: $item")
            setter.call(item)
            binding.executePendingBindings()
        }
    }

}


