package com.example.android.dogtionary.adapter

import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.dogtionary.R
import com.example.android.dogtionary.databinding.ViewItemBinding
import com.example.android.dogtionary.network.DogPhoto

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
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

/**
 * Updates the data shown in the [RecyclerView].
 */
//TODO fix binding so adapter displays data. what can replace submit list?
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: MutableLiveData<DogPhoto>?){
    val adapter = recyclerView.adapter as DogPhotoGridAdapter
    adapter.notifyDataSetChanged()
}
/** JSON
 * {
"message": "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3042.jpg",
"status": "success"
}*/
@BindingAdapter("statusResponse")
fun bindStatus(textView: EditText, status:String?) {
    status.let {
        if (textView.text.toString() == "") {
            textView.error = "Enter search term"
        }
    }
}