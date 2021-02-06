package pl.nluk.papuvege.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import pl.nluk.papuvege.models.MenuDisplay
import pl.nluk.papuvege.models.MenuItem
import pl.nluk.papuvege.services.MenuService

class MenuViewModel : ViewModel() {

    private val menuService: MenuService by inject(MenuService::class.java)
    var menuItems : LiveData<List<MenuDisplay>> = MutableLiveData<List<MenuDisplay>>()

    fun fetchMenu(){
        menuItems = liveData{
            emit(menuService.fetchMenu())
        }
    }



}