package com.example.android.dogtionary.chapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import coil.load
import com.example.android.dogtionary.databinding.FragmentDogPhotoLayoutBinding

/**
 * [RecyclerView.Adapter] that can display a [DogPhoto].
 */
class DogPhotoListAdapter(private val listener: Listener, private val photoList: List<String>) :
    RecyclerView.Adapter<DogPhotoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentDogPhotoLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = photoList.size

    inner class ViewHolder(private var binding: FragmentDogPhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val dogImageView: ImageView = binding.dogImageView
        fun bind(dogPhoto: String) {
            dogImageView.load(dogPhoto)
        }

        init {
            dogImageView.setOnClickListener {
                listener.onImageClicked(absoluteAdapterPosition)
            }
        }
    }

    interface Listener {
        fun onImageClicked(index: Int)
    }
}