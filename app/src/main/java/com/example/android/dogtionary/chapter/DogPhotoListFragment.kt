package com.example.android.dogtionary.chapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.dogtionary.databinding.FragmentDogPhotoLayoutBinding
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.model.DogViewModel

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
        viewModel.dogPhoto.observe(viewLifecycleOwner, {
            val dogPhotoList = it.imageUrl
            adapter = DogPhotoListAdapter(this, dogPhotoList!!)
            binding.photosGrid.adapter = adapter
        })

    }

    override fun onImageClicked(index: Int) {
        val dogBinding = FragmentDogPhotoLayoutBinding.inflate(layoutInflater)
        dogBinding.dogImageView.setOnClickListener {
            val action =
                DogPhotoListFragmentDirections.actionDogPhotoListFragmentToDogsDisplayFragment()
            adapter.notifyItemChanged(index)
            findNavController().navigate(action)
        }
    }
}