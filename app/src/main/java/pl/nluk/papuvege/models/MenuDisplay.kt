package pl.nluk.papuvege.models

interface MenuDisplay {
    val type : Int

    companion object TYPES{
        const val ITEM = 0
        const val CATEGORY = 1
    }
}