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

        viewModel.dogPhoto.observe(this, {
            val imgUri = it.messageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)

            val status = it.statusResponse!!
            binding.textView.text = status
        })
    }
}