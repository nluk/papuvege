package pl.nluk.papuvege.services

import pl.nluk.papuvege.models.MenuDisplay
import pl.nluk.papuvege.models.MenuItem


interface MenuService {
    suspend fun fetchMenu() : List<MenuDisplay>
}