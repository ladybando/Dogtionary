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

    private val _status = MutableLiveData<DogPhoto>()
    val status:LiveData<DogPhoto> = _status

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
                _dogPhoto.value = DogPhotoApi.retrofitService.getPhotoByBreed(breedType!!)
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }

}