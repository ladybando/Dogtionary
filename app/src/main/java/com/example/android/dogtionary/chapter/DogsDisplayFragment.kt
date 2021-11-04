package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.adapter.PhotoGridAdapter
import com.example.android.dogtionary.databinding.FragmentDogsDisplayBinding

class DogsDisplayFragment : Fragment(), PhotoGridAdapter.Listener {

    private val viewModel: DogViewModel by activityViewModels()

    private var _binding: FragmentDogsDisplayBinding? = null
    private val binding get() = _binding!!

    /*TODO https://developer.android.com/codelabs/kotlin-android-training-diffutil-databinding#2
    *  TODO: decide what dog information to display
    *  TODO: decide how it will be stored -string resource, data class, etc
    *  TODO: decide how it will be displayed: how will it link to picture?
    *    using breed string? what if string is not the right string? should it be checked against an array of prepopulated strings?
    * TODO display data in second fragment that is an imageview and textviews https://material.io/components/cards/android#card */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDogsDisplayBinding.inflate(layoutInflater)

        binding.photosGrid.adapter = PhotoGridAdapter(this)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

       showRandomPhoto()

        return binding.root
    }

    private fun showRandomPhoto() {
        viewModel.getNewPhoto()
    }
//not showing pictures atm
    /*
    private fun showPhotoByBreed() {
    binding.dogImageView.setOnClickListener {
        val textView = binding.editTextDogBreed
        //shows text view and clear button for breed searches
        //allows enter key to initiate search by calling getPhotoByBreed() method
        textView.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                //close keyboard after key press
                textView.hideKeyboard()
                //calls getPhotoByBreed() method if text view has characters && if the statusResponse is "success"
                if (textView.text.isNotEmpty() && !viewModel.status.value.equals("success")) {
                    //viewModel.getPhotoByBreed(textView.text.toString().lowercase())
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
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }*/
}