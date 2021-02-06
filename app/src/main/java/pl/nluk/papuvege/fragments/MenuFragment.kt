package pl.nluk.papuvege.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import pl.nluk.papuvege.MenuItemAdapter
import pl.nluk.papuvege.R
import pl.nluk.papuvege.databinding.FragmentMenuBinding
import pl.nluk.papuvege.viewmodels.MenuViewModel


class MenuFragment : DataBindingFragment() {

    override val layoutId: Int = R.layout.fragment_menu
    private val menuViewModel : MenuViewModel by viewModels()
    private lateinit var menuItemAdapter : MenuItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuViewModel.menuItems.observe(viewLifecycleOwner) {
            Log.d("MENU_FRAG", "Observed: $it")
            menuItemAdapter.items = it
            menuItemAdapter.notifyDataSetChanged()
        }
        initRecycler()
        menuViewModel.fetchMenu()
        menuViewModel.menuItems.observe(viewLifecycleOwner) {
            menuItemAdapter.items = it
            menuItemAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler(){
        menuItemAdapter = MenuItemAdapter(emptyList())
        with(binding<FragmentMenuBinding>()){
            recMenu.adapter = menuItemAdapter
            recMenu.layoutManager = LinearLayoutManager(this@MenuFragment.context)
        }
    }

}