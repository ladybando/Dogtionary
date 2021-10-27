package com.example.android.thedoggiesaurus

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.example.android.thedoggiesaurus.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val randomPhotoButton = binding.submitButton
        val textView = binding.editTextBreed
        val byBreedButton = binding.byBreedButton
        val clearButton = binding.clearText
        clearButton.visibility = View.INVISIBLE

        viewModel.dogPhoto.observe(this, {
            val imgUri = it.messageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)

        })
        randomPhotoButton.setOnClickListener {
            viewModel.getNewPhoto()
        }
        byBreedButton.setOnClickListener {
            textView.visibility = View.VISIBLE
            clearButton.visibility = View.VISIBLE
            byBreedButton.visibility = View.INVISIBLE
            textView.setOnKeyListener { _, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                    viewModel.getPhotoByBreed(textView.text.toString().lowercase())
                    return@setOnKeyListener true
                } else {
                    false
                }
            }

        }
        clearButton.setOnClickListener {
            textView.text.clear()
            textView.visibility = View.INVISIBLE
            clearButton.visibility = View.INVISIBLE
            byBreedButton.visibility = View.VISIBLE
        }

        //if (viewModel.dogPhoto.observe(this,{it.statusResponse!!}).toString() != "error") //what to check against?{
        //viewModel.getPhotoByBreed(textView.text.toString().lowercase()) //crashes if search term is not correct
        /* } else {
                 Snackbar
                     .make(this,textView, "Rotten doggy treats! Try another search term!", Snackbar.LENGTH_SHORT)
                     .show()
             }*/
    }
}
