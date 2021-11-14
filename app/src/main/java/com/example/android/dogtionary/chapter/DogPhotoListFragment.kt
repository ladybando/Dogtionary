package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.dogtionary.databinding.FragmentDogPhotoLayoutBinding
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.model.DogViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of DogPhotos.
 */
class DogPhotoListFragment : Fragment(), DogPhotoListAdapter.Listener {
    private var _binding: FragmentDogPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogPhotoListAdapter
    private val viewModel: DogViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDogPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dogPhoto.observe(viewLifecycleOwner, { dogPhoto ->
            val dogPhotoList = dogPhoto.imageUrl
            adapter = DogPhotoListAdapter(this, dogPhotoList!!)
            binding.photosGrid.adapter = adapter
        })

    }

    override fun onImageClicked(index: Int) {
        viewModel.dogPhoto.observe(this.requireActivity(), {
            val dogPhoto = it.imageUrl!![index]
            Log.d("Fragment", dogPhoto)
            val action = DogPhotoListFragmentDirections
                .actionDogPhotoListFragmentToDogsDisplayFragment(dogPhoto)
            adapter.notifyItemChanged(index)
            findNavController().navigate(action)
        })

    }
}