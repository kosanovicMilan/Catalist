package com.example.catalist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedListViewModel(
    private val reposytory : BreedsRepository = BreedsRepository
) : ViewModel(){

    private val _state = MutableStateFlow(BreedListContract.BreedListState())
    val state = _state.asStateFlow()
    private fun setState(
        reducer: BreedListContract.BreedListState.() -> BreedListContract.BreedListState
    ) = _state.update(reducer)

    init {
        fetchAllBreeds()
    }

    private fun fetchAllBreeds(){
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val breeds = withContext(Dispatchers.IO){
                    reposytory.fetchAllBreeds().map { it.asBreedUiModel() }
                }

                setState { copy(breeds = breeds) }

            } catch (error: Exception) {

            } finally {
                setState { copy(loading = false) }
            }
        }
    }

    private fun BreedsApiModel.asBreedUiModel() = CatUIData(
        id = this.id,
        name = this.name,
        description = this.description,
        life_span = this.life_span,
        url = this.url,
        origin = this.origin,
        temperament = this.temperament,
        trait = this.trait

    )
}