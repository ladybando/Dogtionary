package com.example.android.dogtionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.android.dogtionary.databinding.FragmentDogsDisplayBinding

class DogsDisplayFragment : Fragment() {
    /*
    *  TODO: decide what dog information to display
    *  TODO: decide how it will be stored -string resource, data class, etc. most likely database
    *  TODO: decide how it will be linked to picture?
    *    using breed string? what if string is not the right string? should it be checked against an array of pre-populated strings?
    * set blur and rounded corners to images https://coil-kt.github.io/coil/transformations/
    */
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
        val passedPhoto = args.passedBackDogPhoto
        dogImageView.load(passedPhoto)

        binding.nextButton.setOnClickListener {
            val action = DogsDisplayFragmentDirections.actionDogsDisplayFragmentToDogPhotoListFragment(
                    passedPhoto)
            findNavController().navigate(action)
        }
    }
}