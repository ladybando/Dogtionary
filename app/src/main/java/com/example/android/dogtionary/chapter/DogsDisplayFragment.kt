package com.example.android.dogtionary.chapter

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.R
import com.example.android.dogtionary.adapter.DogPhotoGridAdapter
import com.example.android.dogtionary.databinding.FragmentDogDisplayBinding
import com.google.android.material.snackbar.Snackbar

class DogsDisplayFragment : Fragment(), DogPhotoGridAdapter.Listener {

    private val viewModel: DogViewModel by activityViewModels()

    private var _binding: FragmentDogDisplayBinding? = null
    private val binding get() = _binding!!

    /*
    *  TODO: decide what dog information to display
    *  TODO: decide how it will be stored -string resource, data class, etc
    *  TODO: decide how it will be displayed: how will it link to picture?
    *    using breed string? what if string is not the right string? should it be checked against an array of prepopulated strings?
    * TODO display data in second fragment that is an imageview and textviews https://material.io/components/cards/android#card */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDogDisplayBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.photosGrid.adapter = DogPhotoGridAdapter(this)

        showPhotoByBreed()

        return binding.root
    }
//not showing pictures atm
    private fun showPhotoByBreed() {
        binding.photosGrid.setOnClickListener {
            //shows text view and clear button for breed searches
            //allows enter key to initiate search by calling getPhotoByBreed() method
/*            textView.setOnKeyListener { _, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                    //close keyboard after key press
                    textView.hideKeyboard()
                    //calls getPhotoByBreed() method if text view has characters && if the statusResponse is "success"
                    if (textView.text.isNotEmpty() && !viewModel.status.value.equals("success") ) {*/
                        //viewModel.getPhotoByBreed(textView.text.toString().lowercase())
                  /*  } else {
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
                }*/
            }
        }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onTaskClicked(index: Int) {
        viewModel.getPhotoByBreed("hound")
    }

}