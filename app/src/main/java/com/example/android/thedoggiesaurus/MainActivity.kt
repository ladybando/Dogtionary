package com.example.android.thedoggiesaurus

 import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.example.android.thedoggiesaurus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.editTextBreed.text.toString()
        viewModel.dogPhoto.observe(this, {
            val imgUri = it.messageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)

        })
        binding.button.setOnClickListener {
            if (textView.isNotEmpty()) {
                viewModel.getPhotoByBreed(textView.lowercase())
            } else {
                viewModel.getNewPhoto()
            }
        }
    }
}