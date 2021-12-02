package com.example.android.dogtionary.data

import android.app.Application

class DogImageApplication : Application() {
    val database: DogDatabase by lazy { DogDatabase.getDatabase(this) }
}