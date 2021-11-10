package com.example.android.dogtionary.adapter


import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.dogtionary.R
import com.example.android.dogtionary.chapter.DogPhotoListAdapter
import com.example.android.dogtionary.network.DogPhoto
import okhttp3.internal.notify


/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("messageUrl")
fun bindImage(dogImgView: ImageView, imagesUrlList: List<String>?) {
    imagesUrlList?.let {
        for (imageUrl in imagesUrlList) {
            dogImgView.load(imageUrl.toUri().buildUpon().scheme("https").build()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }
}
@BindingAdapter("dogList")
fun bindImageList(recyclerView: RecyclerView, listData: DogPhoto){
    //listData is null? when using adapter.submitList(listData) get error of null getItem(position) in photo
    //grid adapter
    val adapter = recyclerView.adapter as DogPhotoListAdapter
    adapter.notify()
}
/** JSON
 * {
"message": "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3042.jpg",
"status": "success"
}*/
@BindingAdapter("statusResponse")
fun bindStatus(textView: EditText, status:String?) {
    status.let {
        if (textView.text.toString().isDigitsOnly() || textView.text.toString().isBlank()) {
            textView.error = "Enter search term"
        }
    }
}