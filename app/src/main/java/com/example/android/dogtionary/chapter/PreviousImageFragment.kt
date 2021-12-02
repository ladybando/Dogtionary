package com.example.android.dogtionary.chapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.dogtionary.R
import com.example.android.dogtionary.databinding.FragmentPreviousImageBinding

/*
 * Fragment class that displays previous image selected
 */
class PreviousImageFragment : Fragment() {
    private var _binding : FragmentPreviousImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPreviousImageBinding.inflate(inflater, container, false)
        return binding.root
    }
}