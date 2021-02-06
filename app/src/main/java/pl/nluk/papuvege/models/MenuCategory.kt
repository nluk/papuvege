package pl.nluk.papuvege.models

data class MenuCategory(
    val name : String
) : MenuDisplay {
    override val type: Int = MenuDisplay.CATEGORY
    override fun toString(): String = "Category $name"
}
