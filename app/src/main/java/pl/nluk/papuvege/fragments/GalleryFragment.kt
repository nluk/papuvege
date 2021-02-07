package pl.nluk.papuvege.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*
import pl.nluk.papuvege.R
import pl.nluk.papuvege.adapters.ImageAdapter
import pl.nluk.papuvege.binding.setImageUrl

class GalleryFragment : BaseFragment() {

    companion object {
        val URIS = (1..7).map { "https://nluk.me/files/img/$it.jpg" }
    }

    override val layoutId: Int = R.layout.fragment_gallery


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(thumbnailsRecycler){
            adapter = ImageAdapter(URIS){ view, s ->
                setImageUrl(image, s)
            }
            layoutManager = LinearLayoutManager(this@GalleryFragment.context, LinearLayoutManager.HORIZONTAL, false)
        }
        thumbnailsRecycler.adapter?.notifyDataSetChanged()
    }
}