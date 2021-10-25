package com.example.android.thedoggiesaurus.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.thedoggiesaurus.R
import com.example.android.thedoggiesaurus.databinding.FragmentDogDisplayBinding

class DogDisplayFragment : Fragment() {

    private val viewModel: DogViewModel by activityViewModels()
    private lateinit var button: Button
    private lateinit var userInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentDogDisplayBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_display, container, false)
        binding.viewModel = viewModel

        button = binding.button
        userInput = binding.editTextDogBreed
        //val userInputtedBreed = userInput.text.toString()
        button.setOnClickListener {
           /* if (userInputtedBreed.isNotEmpty()) {
                viewModel.getNewPhotoByBreed(userInputtedBreed)
            }else {*/
                viewModel.getNewPhoto()
                //null pointer exception from viewModel
         /*   }*/
        }
        return binding.root
    }


}