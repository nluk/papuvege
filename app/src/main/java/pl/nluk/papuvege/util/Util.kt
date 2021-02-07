package pl.nluk.papuvege.util

import android.view.View
import kotlin.reflect.KFunction

fun View.onClick(listener : KFunction<*>){
    this.setOnClickListener {
        listener.call()
    }
}
