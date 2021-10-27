package com.example.android.thedoggiesaurus.chapter

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.thedoggiesaurus.R
import com.example.android.thedoggiesaurus.databinding.FragmentDogDisplayBinding
import com.google.android.material.snackbar.Snackbar

class DogDisplayFragment : Fragment() {

    private val viewModel: DogViewModel by activityViewModels()
    private lateinit var button: Button
    private lateinit var breedButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentDogDisplayBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dog_display, container, false)
        binding.viewModel = viewModel

        button = binding.submitButton
        breedButton = binding.byBreedButton
        val textView = binding.editTextDogBreed
        val clearButton = binding.clearText

        button.setOnClickListener {
            viewModel.getNewPhoto()
        }
        breedButton.setOnClickListener {
            //shows text view and clear button for breed searches
            textView.visibility = View.VISIBLE
            clearButton.visibility = View.VISIBLE
            breedButton.visibility = View.INVISIBLE
            //allows enter key to initiate search by calling getPhotoByBreed() method
            textView.setOnKeyListener { _, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                    //calls method if text view has characters and if the statusResponse is success
                    if (textView.text.isNotBlank() && viewModel.status.value!!.statusResponse == "success") {
                        viewModel.getPhotoByBreed(textView.text.toString().lowercase())
                    } else {
                        Snackbar
                            .make(this.requireContext(),
                                textView,
                                "Rotten doggy treats! Try another search term!",
                                Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    return@setOnKeyListener true
                } else {
                    false
                }
            }

        }
        //clears text view and button once no longer in use
        clearButton.setOnClickListener {
            textView.text.clear()
            textView.visibility = View.INVISIBLE
            clearButton.visibility = View.INVISIBLE
            breedButton.visibility = View.VISIBLE
        }
        return binding.root
    }


}