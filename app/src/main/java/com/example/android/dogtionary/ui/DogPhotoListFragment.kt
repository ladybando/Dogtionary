package com.example.android.dogtionary.ui

import android.app.SearchManager
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.contentcapture.ContentCaptureCondition
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.dogtionary.R
import com.example.android.dogtionary.adapter.DogPhotoListAdapter
import com.example.android.dogtionary.data.Dog
import com.example.android.dogtionary.data.DogImageApplication
import com.example.android.dogtionary.databinding.FragmentDogPhotoListBinding
import com.example.android.dogtionary.model.ImagesViewModel
import com.example.android.dogtionary.model.ImagesViewModelFactory
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of DogPhotos.
 */

/*something is not lifecycle aware https://stackoverflow.com/questions/55631819/fragment-not-associated-with-a-fragment-manager
* if i click the favorites button in the image and then the search view and then search for a dog breed it crashes*/
//todo add database functionality for favorites only
class DogPhotoListFragment : Fragment(), DogPhotoListAdapter.Listener {

    private var _binding: FragmentDogPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogPhotoListAdapter
    private val viewModel: ImagesViewModel by activityViewModels {
        ImagesViewModelFactory((activity?.application as DogImageApplication).database.dogDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentDogPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dogPhoto.observe(viewLifecycleOwner) {
            val dogPhotoList = it.imageUrl
            adapter = DogPhotoListAdapter(this, dogPhotoList!!)
            binding.photosGrid.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)
        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView

        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        (searchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        }

        //change search icon color
        val searchImgId = resources.getIdentifier("android:id/search_button", null, null)
        val searchIcon = searchView.findViewById<ImageView>(searchImgId)
        searchIcon.setImageResource(R.drawable.ic_breed_search)

        //change close button color
        val closeImgId = resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeIcon = searchView.findViewById<ImageView>(closeImgId)
        closeIcon.setImageResource(R.drawable.ic_clear)

        //change EditText text color
        val searchTextId =
            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val textView = searchView.findViewById<TextView>(searchTextId)
        textView.setTextColor(Color.BLACK)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userQuery: String?): Boolean {
                //Called when this view wants to give up focus
                searchView.clearFocus()
                //Returns the query string currently in the text field. checks if string is empty?
                searchView.setQuery("", false)
                search.collapseActionView()
                if (!viewModel.status.value.equals("success")) {
                    viewModel.getPhotoByBreed(userQuery)
                } else {
                    Snackbar
                        .make(
                            requireActivity(),
                            requireActivity().findViewById(R.id.cl),//needs to be SearchView textView
                            "Rotten doggy treats, try another search term!",
                            Snackbar.LENGTH_SHORT
                        )
                        .show()
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.favorite -> {

            //may need to have call to action out of for loop
            //when user clicks favorites open favorites list and show images from database
            viewModel.getAllImagesList().observe(this) { dbPhotoList ->
                for (photo in dbPhotoList) {
                    //pass bundle of images to [FavoritesFragment] to display to user
                    //set [photo] toString(); db key needs string object not dog object
                    val action =
                        DogPhotoListFragmentDirections.actionDogPhotoListFragmentToFavoriteListFragment(
                            photo.toString()
                        )
                    findNavController().navigate(action)
                }
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    override fun onImageClicked(index: Int) {
        viewModel.dogPhoto.observe(this.requireActivity()) {
            val dogPhoto = it.imageUrl!![index]
            val action =
                DogPhotoListFragmentDirections.actionDogPhotoListFragmentToDogsDisplayFragment(
                    dogPhoto
                )
            adapter.notifyItemChanged(index)
            findNavController().navigate(action)
        }
    }

    override fun onImageButtonClickAdd(view: ImageButton, index: Int) {
        //when user clicks heart on image, change color and add image to database
        val dogImage = Dog(viewModel.dogPhoto.value!!.imageUrl!![index])
        view.setImageResource(R.drawable.ic_favorite)
        viewModel.insertDogImage(dogImage)
    }
}