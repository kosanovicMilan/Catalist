package com.example.catalist.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.apiCall.model.ImageApiModel
import com.example.catalist.uimodel.CatUIData
import com.example.catalist.repository.BreedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    init {
        fetchOneBreed(breedId)
    }
    private fun fetchOneBreed(breedId: String){
        viewModelScope.launch{
                setState { copy(loading = true, breedId = breedId) }

            try {

                val breed = withContext(Dispatchers.IO){
                    repository.fetchOneBreed(breedId)
                }
                Log.d("macaClick","navodno je dohvatio u view modelu...")

                Log.d("BreedList","Ovo sam pokusao id ${breed.id}")
                val image = withContext(Dispatchers.IO){
                    repository.getBreedImage(breed.id)
                }
                val trIm = image[0]
                val trueBreed = breed.asBreedUiModel(trIm)

                setState { copy(breed = trueBreed,breedId = breedId) }
                delay(1000)
                Log.d("BreedList","ovo je jedan breed $trueBreed")
            }catch (error : Exception){

                Log.d("ERRONE","Nije dobro nesto: $error")
                setState { copy(error = true) }
            }finally {

                setState { copy(loading = false,breedId = breedId) }
            }

        }
    }

    private fun BreedsApiModel.asBreedUiModel(image:ImageApiModel) = CatUIData(
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
        image_url = image.url


    )
}