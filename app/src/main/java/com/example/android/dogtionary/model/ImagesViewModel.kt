package com.example.android.dogtionary.model

import androidx.lifecycle.*
import com.example.android.dogtionary.data.Dog
import com.example.android.dogtionary.data.DogDao
import com.example.android.dogtionary.network.*
import kotlinx.coroutines.launch

class ImagesViewModel(private val dogDao: DogDao) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _dogPhoto = MutableLiveData<DogPhoto>()
    // The external immutable LiveData for the request status
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    private val _status = MutableLiveData<String>()
    val status:LiveData<String> = _status

    private val _unsplashPhoto = MutableLiveData<UnsplashPhoto>()
    val unsplashPhoto: LiveData<UnsplashPhoto> = _unsplashPhoto

    /**
     * Call getNewPhoto() on init so we can display status immediately.
     */
    init {
        getNewPhoto()
    }

    /**
     * Gets dog photos information from the Dog API Retrofit service
     */
    private fun getNewPhoto() {
        try {
            viewModelScope.launch {
                _dogPhoto.value = DogPhotoApi.retrofitService.getRandomPhoto()
                _unsplashPhoto.value = UnsplashApi.retrofitService.getRandomImage()
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

    fun insertDogImage(dog: Dog){
        viewModelScope.launch {
            dogDao.insertImage(dog)
        }
    }

    fun deleteImage(dog: Dog){
        viewModelScope.launch {
            dogDao.deleteImage(dog)
        }
    }

    fun getImage(imageUrl: String){
        viewModelScope.launch {
            dogDao.getImage(imageUrl)
        }
    }

    fun getAllImagesList(): LiveData<List<Dog>>{
        return dogDao.getAllImages().asLiveData()
    }
}

class ImagesViewModelFactory(private val dogDao: DogDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImagesViewModel(dogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}