package pl.nluk.papuvege.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.view.children
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_about.*
import pl.nluk.papuvege.R
import pl.nluk.papuvege.util.onClick


class AboutFragment : BaseFragment() {

    private companion object {
        const val PAPUVEGE_EMAIL = "papuvege@gmail.com"
        const val PAPUVEGE_MOBILE ="+48574021180"
        const val TAG = "FG_ABOUT"
        const val STREET_VIEW_URI = "google.streetview:cbll=51.7697179,19.4556709"
        const val SEND_EMAIL = "Send email"
        const val TRANSPARENT_RED = 0x66ebb4a9
    }

    override var layoutId: Int = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailInfo.onClick(this::emailIntent)
        mapInfo.onClick(this::mapIntent)
        mobileInfo.onClick(this::callIntent)
        sendContactForm.onClick(this::sendForm)
    }

    fun emailIntent(){
        with(Intent(Intent.ACTION_SEND)){
            data = Uri.parse("mailto:$PAPUVEGE_EMAIL")
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(PAPUVEGE_EMAIL))
            runIntentIfResolvable(this, chooser = true, name = SEND_EMAIL)
        }
    }

    fun mapIntent(){
        with(Intent(Intent.ACTION_VIEW, Uri.parse(STREET_VIEW_URI))){
            setPackage("com.google.android.apps.maps")
            runIntentIfResolvable(this)
        }
    }

    fun callIntent(){
        with(Intent(Intent.ACTION_DIAL)){
            data = Uri.parse("tel:$PAPUVEGE_MOBILE")
            runIntentIfResolvable(this)
        }
    }

    fun sendForm(){
        if(allNotEmpty(formBody, formEmail, formFirstNameLastName, formTopic)){
            contactForm.children.mapNotNull { it as? EditText }.forEach { it.text = null; it.setBackgroundColor(Color.WHITE) }
            Snackbar.make(rootLayout, getString(R.string.form_sent), LENGTH_SHORT).show()
        }
        else{
            Snackbar.make(rootLayout, getString(R.string.fill_required_fields), LENGTH_SHORT).show()
        }

    }

    private fun runIntentIfResolvable(intent : Intent, chooser: Boolean = false, name : String = ""){
        intent.also {
            activity?.packageManager?.also { packageManager ->
                it.resolveActivity(packageManager)?.run { startActivity(if(chooser) Intent.createChooser(it, name) else it) } ?: Log.d(TAG, "$it not started")
            }
        }
    }

    fun allNotEmpty(vararg views : EditText) : Boolean {
        var allNotEmpty = true
        for(editText in views){
            if(editText.text.isNullOrBlank()){
                editText.setBackgroundColor(TRANSPARENT_RED)
                allNotEmpty = false
            }
            else{
                editText.setBackgroundColor(Color.WHITE)
            }
        }
        return allNotEmpty
    }
}