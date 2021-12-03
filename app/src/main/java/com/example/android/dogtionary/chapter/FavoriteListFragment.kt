package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.R
import com.example.android.dogtionary.data.Dog
import com.example.android.dogtionary.data.DogImageApplication
import com.example.android.dogtionary.databinding.FragmentFavoriteListBinding
import com.example.android.dogtionary.model.DogViewModel
import com.example.android.dogtionary.model.DogViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class FavoriteListFragment : Fragment(), FavoriteListAdapter.Listener  {
    private var _binding : FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteListAdapter
    private val viewModel: DogViewModel by activityViewModels {
        DogViewModelFactory((activity?.application as DogImageApplication).database.dogDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dogPhoto.observe(viewLifecycleOwner, {
            val favoriteImageList = it.imageUrl
            adapter = FavoriteListAdapter(this, favoriteImageList!!)
            binding.favoriteGrid.adapter = adapter
        })
    }

    override fun onFavButtonClicked(view: ImageButton, index: Int) {
        super.onFavButtonClicked(view, index)
        /*When user clicks fav button, remove image from database*/
        val favImage = Dog(viewModel.dogPhoto.value!!.imageUrl!![index])
        view.setImageResource(R.drawable.ic_not_favorite)
        viewModel.deleteImage(favImage)
    }
}