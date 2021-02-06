package pl.nluk.papuvege.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.nluk.papuvege.models.Order

class MainViewModel : ViewModel() {
    val order : MutableLiveData<Order> by lazy {
        MutableLiveData<Order>()
    }
}