package pl.nluk.papuvege.services.impl


import pl.nluk.papuvege.api.MenuApi
import pl.nluk.papuvege.models.MenuCategory
import pl.nluk.papuvege.models.MenuDisplay
import pl.nluk.papuvege.models.MenuItem
import pl.nluk.papuvege.services.MenuService

class MenuServiceImpl(
    val menuApi : MenuApi
) : MenuService {
    override suspend fun fetchMenu(): List<MenuDisplay> {
        val menu = ArrayList<MenuDisplay>()
        val response = menuApi.getMenu()
        for (category in response){
            menu += MenuCategory(category.name)
            for (item in category.items){
                val menuItem = MenuItem()
                with(menuItem){
                    price = item.price
                    description = item.description ?: ""
                    name = item.name
                    imageURL = item.img
                }
                menu += menuItem
            }
        }
        return menu
    }
}