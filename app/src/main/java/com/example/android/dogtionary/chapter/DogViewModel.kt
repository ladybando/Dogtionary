package com.example.android.dogtionary.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.dogtionary.network.DogPhoto
import com.example.android.dogtionary.network.DogPhotoApi
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    private val _dogBreedPhoto = MutableLiveData<DogPhoto>()
    val dogBreedPhoto: LiveData<DogPhoto> = _dogBreedPhoto

    private val _dogPhoto = MutableLiveData<List<DogPhoto>>()
    val dogPhoto: LiveData<List<DogPhoto>> = _dogPhoto

    private val status = MutableLiveData<String>()

    init {
        getNewPhoto()
    }

    fun getNewPhoto() {
        try {
            viewModelScope.launch {
                _dogPhoto.value = DogPhotoApi.retrofitService.getRandomPhoto()
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }

    fun getPhotoByBreed(breedType: String?) {
        try {
            viewModelScope.launch {
                val response = DogPhotoApi.retrofitService.getPhotoByBreed(breedType!!)
                _dogBreedPhoto.value = response
                status.value = response.statusResponse!!
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }
}
