package com.example.catalist.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.uimodel.CatUIData
import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.repository.BreedsRepository
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

                val breed = reposytory.fetchOneBreed("abys")

                Log.d("OneBread","dohvatio nesto $breed")
                setState { copy(breeds = breeds) }

                Log.d("BreedList","Dohatio rase: $breeds")

            } catch (error: Exception) {
                Log.e("BreedList","PUKLO JE NESTO",error)
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
        alt_names = this.alt_names,
        social_needs = this.social_needs,
        wikipedia_url = this.wikipedia_url

    )
}