package eu.example.myrecipeapp2

data class Category(
    val idString: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class CategoryResponse(
    val categories: List<Category>
)