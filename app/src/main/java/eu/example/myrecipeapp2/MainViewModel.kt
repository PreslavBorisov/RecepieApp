package eu.example.myrecipeapp2

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categoriesState= mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoriesState


    init {
        fetchCategory()
    }

    private fun fetchCategory(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value=_categoriesState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )

            }catch (e: Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = emptyList(),
                    error = "Error fetching a Category ${e.message}"
                )

            }

        }
    }
    data class RecipeState(
        val loading: Boolean =true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}


