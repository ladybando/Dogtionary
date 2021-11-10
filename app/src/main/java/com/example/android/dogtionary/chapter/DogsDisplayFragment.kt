package com.example.android.dogtionary.chapter

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.databinding.FragmentDogsDisplayBinding
import com.example.android.dogtionary.model.DogViewModel
import com.google.android.material.snackbar.Snackbar

class DogsDisplayFragment : Fragment() {

    private val viewModel: DogViewModel by activityViewModels()
    private lateinit var button: Button
    private lateinit var breedButton: ImageButton
    private lateinit var textView: TextView
    private lateinit var clearButton: ImageButton

    private var _binding: FragmentDogsDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDogsDisplayBinding.inflate(inflater, container, false)


        button = binding.submitButton
        breedButton = binding.byBreedButton
        textView = binding.editTextDogBreed
        clearButton = binding.clearText

        showRandomPhoto()
        showPhotoByBreed()

        return binding.root
    }

    private fun showRandomPhoto() {
        /*Observes dogPhoto from View Model and uses the Coil library
         * to load an image into an [ImageView].*/
/*        viewModel.dogPhoto.observe(this.requireActivity(), {
            val dogPhotoList = it.imageUrl
            for (dogPhoto in dogPhotoList!!) {
                val imgUri = dogPhoto.toUri().buildUpon().scheme("https").build()
                binding.dogImageView.load(imgUri)
            }
        })*/
        button.setOnClickListener {
            viewModel.getNewPhoto()
        }

    }
    private fun showPhotoByBreed() {
        breedButton.setOnClickListener {
            //shows text view and clear button for breed searches
            textView.visibility = View.VISIBLE
            clearButton.visibility = View.VISIBLE
            breedButton.visibility = View.INVISIBLE
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
            //clears text view and button once no longer in use
            clearButton.setOnClickListener {
                binding.editTextDogBreed.text.clear()
                textView.visibility = View.INVISIBLE
                clearButton.visibility = View.INVISIBLE
                breedButton.visibility = View.VISIBLE
            }
        }
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}