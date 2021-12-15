package com.example.android.dogtionary.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android.dogtionary.R
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.databinding.FragmentUnsplashDetectImageBinding
import com.google.android.material.snackbar.Snackbar

class UnsplashDetectImageFragment : Fragment() {
    private var _binding: FragmentUnsplashDetectImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUnsplashDetectImageBinding.inflate(inflater, container, false)
        return binding.root
    }
    /*
    * get access to camera app with image button
    * take picture and return as BitMap
    * apply image rec to photo
    * apply image labeling, text imaging, face recognition
    * use codelab to add boxes around items in image: https://codelabs.developers.google.com/mlkit-android-odt#5
    * decide how to get to this fragment button, from menu option, etc*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener { openCamera() }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        val imageBitmap = it.data!!.extras!!.get("data") as Bitmap
        if (it.resultCode == Activity.RESULT_OK) {
            binding.imageView.setImageBitmap(imageBitmap)
        }
        binding.imageView.setOnClickListener {
            Snackbar.make(binding.imageView, "You clicked me!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            getResult.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            Log.i("ImageFrag1", "Error message: $e")
        }
    }
}