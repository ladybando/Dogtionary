package com.example.android.dogtionary.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.android.dogtionary.data.DogImageApplication
import com.example.android.dogtionary.databinding.FragmentUnsplashDetectImageBinding
import com.example.android.dogtionary.mlkit.MlKitDetection
import com.example.android.dogtionary.model.ImagesViewModel
import com.example.android.dogtionary.model.ImagesViewModelFactory

class UnsplashDetectImageFragment : Fragment() {
    private var _binding: FragmentUnsplashDetectImageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImagesViewModel by activityViewModels {
        ImagesViewModelFactory((activity?.application as DogImageApplication).database.dogDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUnsplashDetectImageBinding.inflate(inflater, container, false)
        return binding.root
    }
    /*get image from unsplsh service not from camera app
    * apply image rec to photo
    * apply text imaging, face recognition
    * use codelab to add boxes around items in image: https://codelabs.developers.google.com/mlkit-android-odt#5
    * decide how to get to this fragment button, from menu option, etc*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pictureButton.setOnClickListener { openCamera() }
        val image = viewModel.unsplashPhoto.value!!.urls.full
        Log.d("unsplashfrag", image)
    }

    private fun objectDetect(imageBitmap: Bitmap){
        binding.imageView.setOnClickListener {
            MlKitDetection().analyze(imageBitmap, binding.textView)
        }
    }

    private fun openCamera() {
        val grabUnsplashImage = viewModel.unsplashPhoto.value!!.urls.full
        binding.imageView.load(grabUnsplashImage)
    }
}