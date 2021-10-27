package com.example.android.thedoggiesaurus

import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("messageUrl")
fun bindImage(dogImgView: ImageView, mssgUrl: String?) {
    mssgUrl?.let {
        val imgUri = mssgUrl.toUri().buildUpon().scheme("https").build()
        dogImgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
