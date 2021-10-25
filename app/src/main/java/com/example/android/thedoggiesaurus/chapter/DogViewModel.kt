package com.example.android.thedoggiesaurus.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.thedoggiesaurus.network.DogPhoto
import com.example.android.thedoggiesaurus.network.DogPhotoApi
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    private val _breed =  MutableLiveData<String>()
    val breed: LiveData<String> = _breed

    init {
       // getNewPhotoByBreed(_breed.value)
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
    /*fun getNewPhotoByBreed(breed: String?) {
        try {
            viewModelScope.launch {
                val dogBreedPhoto = DogPhotoApi.retrofitService.getRandomBreedPhoto(breed!!)//this line throws null pointer exception
                //breed is null obviously, but how to fix?
                _breed.value = dogBreedPhoto.messageUrl!!
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }*/

}