package pl.nluk.papuvege.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.nluk.papuvege.R

abstract class BaseFragment : Fragment() {

    abstract val layoutId : Int

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        onCreateView()
        return inflater.inflate(layoutId, container, false)
    }

    open fun onCreateView(){}
}