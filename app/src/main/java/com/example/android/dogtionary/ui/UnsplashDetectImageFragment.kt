package com.example.android.dogtionary.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
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
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        val imageBitmap = it.data!!.extras!!.get("data") as Bitmap
        if (it.resultCode == Activity.RESULT_OK) {
            binding.imageView.setImageBitmap(imageBitmap)
        }
        binding.imageView.setOnClickListener {
            MlKitDetection().analyze(imageBitmap, binding.textView)
        }
    }

    private fun openCamera() {
        val grabUnsplashImageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      /*  val grabUnsplashImageIntent = Intent(Intent.ACTION_VIEW)
        //needs activity to handle intent. find another option to pass url maybe not intent
        grabUnsplashImageIntent.data = Uri.parse(viewModel.unsplashPhoto.toString())*/
        try {
            getResult.launch(grabUnsplashImageIntent)
        } catch (e: ActivityNotFoundException) {
            Log.i("ImageFrag1", "Error message: $e")
        }
    }
}