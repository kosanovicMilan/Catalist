package com.example.catalist.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.list.model.CatUIData
import com.example.catalist.repository.BreedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatsDetailsViewModel(
    private val breedId : String,
    private val repository : BreedsRepository = BreedsRepository
):ViewModel(){

    private val _state = MutableStateFlow(BreedDetailsContract.BreedDetailsState(breedId = breedId))
        val state = _state.asStateFlow()

    private fun setState(reducer: BreedDetailsContract.BreedDetailsState.() -> BreedDetailsContract.BreedDetailsState ) = _state.getAndUpdate(reducer)

    private fun fetchOneBreed(breedId: String){
        viewModelScope.launch{
                setState { copy(loading = true) }

            try {

                val breed = withContext(Dispatchers.IO){
                    repository.fetchOneBreed(breedId)
                }

                val trueBreed = breed.asBreedUiModel()

                setState { copy(loading = false, breed = trueBreed) }


            }catch (error : Exception){
                setState { copy(error = true) }
                Log.d("ERRONE","Nije dobro nesto: $error")
            }finally {
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
        vocalisation = this.vocalisation

    )
}