package com.example.android.dogtionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.dogtionary.R
import com.example.android.dogtionary.adapter.DogPhotoListAdapter
import com.example.android.dogtionary.data.Dog
import com.example.android.dogtionary.data.DogImageApplication
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.model.DogViewModel
import com.example.android.dogtionary.model.DogViewModelFactory

/**
 * A fragment representing a list of DogPhotos.
 */

//todo add database functionality for favorites only
class DogPhotoListFragment : Fragment(), DogPhotoListAdapter.Listener {
    private var _binding: FragmentDogPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogPhotoListAdapter
    private val viewModel: DogViewModel by activityViewModels {
        DogViewModelFactory((activity?.application as DogImageApplication).database.dogDao())
    }

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
        viewModel.dogPhoto.observe(this.requireActivity(), {
            val dogPhoto = it.imageUrl!![index]
            val action = DogPhotoListFragmentDirections.actionDogPhotoListFragmentToDogsDisplayFragment(
                    dogPhoto
                )
            adapter.notifyItemChanged(index)
            findNavController().navigate(action)
        })
    }

    override fun onImageButtonClickAdd(view: ImageButton, index: Int) {
        //when user clicks heart on image, change color and add image to database
        val dogImage = Dog(viewModel.dogPhoto.value!!.imageUrl!![index])
        view.setImageResource(R.drawable.ic_favorite)
        viewModel.insertDogImage(dogImage)
    }
}