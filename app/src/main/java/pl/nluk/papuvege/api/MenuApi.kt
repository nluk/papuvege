package pl.nluk.papuvege.api

import pl.nluk.papuvege.api.data.WSMenuCategory
import retrofit2.http.GET

interface MenuApi {
    @GET("papuvege.json")
    suspend fun getMenu(): List<WSMenuCategory>
}