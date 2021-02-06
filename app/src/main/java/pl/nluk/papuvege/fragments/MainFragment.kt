package pl.nluk.papuvege.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import pl.nluk.papuvege.R
import pl.nluk.papuvege.databinding.FragmentMainBinding
import pl.nluk.papuvege.viewmodels.MainViewModel
import kotlin.reflect.full.createInstance

class MainFragment : DataBindingFragment() {

    override val layoutId: Int = R.layout.fragment_main
    private lateinit var fragmentAdapter : FragmentAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentAdapter = FragmentAdapter(this)
        val binding = binding<FragmentMainBinding>()
        with(binding){
            pager.adapter = fragmentAdapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = fragmentAdapter.tabName(position)
                pager.isUserInputEnabled = fragmentAdapter.isSwipeEnabled(position)
            }.attach()
        }
        binding.pager.adapter = fragmentAdapter

    }

    class FragmentAdapter(private val fragment : Fragment) : FragmentStateAdapter(fragment){

        companion object {
            val TAB_FRAGMENTS = listOf(
                MenuFragment::class to R.string.menu_menu,
                AboutFragment::class to R.string.menu_about,
                GalleryFragment::class to R.string.menu_gallery,
                DeliveryFragment::class to R.string.menu_delivery
            )
        }

        override fun getItemCount(): Int = TAB_FRAGMENTS.size

        override fun createFragment(position: Int): Fragment = TAB_FRAGMENTS[position].first.createInstance()

        fun tabName(position: Int) : String = fragment.requireContext().getString(TAB_FRAGMENTS[position].second)

        fun isSwipeEnabled(position : Int) = TAB_FRAGMENTS[position].first != DeliveryFragment::class
    }


}