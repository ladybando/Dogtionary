package com.example.android.dogtionary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.dogtionary.R
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.databinding.FragmentUnsplashDetectImageBinding

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
    * use codelab to add boxes around items in image: https://codelabs.developers.google.com/mlkit-android-odt#5*/
}