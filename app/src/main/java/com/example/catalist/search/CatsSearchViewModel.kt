package com.example.catalist.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.list.BreedListContract
import com.example.catalist.repository.BreedsRepository
import com.example.catalist.uimodel.CatUIData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatsSearchViewModel(
 private val repository : BreedsRepository = BreedsRepository
): ViewModel() {

    private val _state = MutableStateFlow(CatsSearchContract.CatsSearchState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatsSearchContract.CatsSearchState.() -> CatsSearchContract.CatsSearchState) = _state.update(reducer)

    init {
        fetchAllBreeds()
    }

    private fun fetchAllBreeds(){
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val breeds = withContext(Dispatchers.IO){
                    repository.fetchAllBreeds().map { it.asBreedUiModel() }
                }

                setState { copy(breeds = breeds) }

                Log.d("BreedList","Dohatio rase iz search: $breeds")
                delay(1000)
            } catch (error: Exception) {
                Log.e("BreedList","PUKLO JE NESTO search",error)
                setState { copy(error = true) }
            } finally {
                setState { copy(loading = false) }
            }
        }
    }

    private fun BreedsApiModel.asBreedUiModel() = CatUIData(
        id = this.id,
        name = this.name,
        temperament = this.temperament,
        origin = this.origin,
        description = this.description,
        life_span = this.life_span,
        weight = this.weight.imperial,
        energy_level = this.energy_level,
        hypoallergenic = this.energy_level,
        grooming = this.grooming,
        intelligence = this.intelligence,
        stranger_friendly = this.stranger_friendly,
        vocalisation = this.vocalisation,
        social_needs = this.social_needs,
        wikipedia_url = this.wikipedia_url,
        image_url = this.image?.url

    )
}