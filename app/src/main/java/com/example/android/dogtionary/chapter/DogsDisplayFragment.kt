package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.android.dogtionary.databinding.FragmentDogsDisplayBinding
import com.example.android.dogtionary.model.DogViewModel

class DogsDisplayFragment : Fragment() {

    private val viewModel: DogViewModel by activityViewModels()
    private var _binding: FragmentDogsDisplayBinding? = null
    private val binding get() = _binding!!
    private lateinit var dogImageView: ImageView
    private val args: DogsDisplayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDogsDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogImageView = binding.dogImageView
        val passedPhoto = args.passedBackDogPhoto.toUri()
        Log.d("DisplayFragment", "$passedPhoto")
        dogImageView.setImageURI(passedPhoto)
    }
    /* private fun showRandomPhoto() {
         *//*Observes dogPhoto from View Model and uses the Coil library
         * to load an image into an [ImageView].*//*
      viewModel.dogPhoto.observe(this.requireActivity(), {
            val dogPhotoList = it.imageUrl
            for (dogPhoto in dogPhotoList!!) {
                val imgUri = dogPhoto.toUri().buildUpon().scheme("https").build()
                binding.dogImageView.load(imgUri)
            }
        })
        button.setOnClickListener {
            viewModel.getNewPhoto()
        }

    }
    private fun showPhotoByBreed() {
        nextButton.setOnClickListener {
            //allows enter key to initiate search by calling getPhotoByBreed() method
            textView.setOnKeyListener { _, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                    //close keyboard after key press
                    textView.hideKeyboard()
                    //calls getPhotoByBreed() method if text view has characters && if the statusResponse is "success"
                    if (textView.text.isNotEmpty() && !viewModel.status.value.equals("success") ) {
                        viewModel.getPhotoByBreed(textView.text.toString().lowercase())
                    } else {
                        Snackbar
                            .make(this.requireContext(),
                                textView,
                                "Rotten doggy treats, try another search term!",
                                Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    return@setOnKeyListener true
                } else {
                    false
                }
            }
        }
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }*/
}