package com.example.android.dogtionary.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.dogtionary.network.DogPhoto
import com.example.android.dogtionary.network.DogPhotoApi
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    private val _status = MutableLiveData<String>()
    val status:LiveData<String> = _status

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
                _dogPhoto.value = response
                _status.value = response.statusResponse!!
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }
}
