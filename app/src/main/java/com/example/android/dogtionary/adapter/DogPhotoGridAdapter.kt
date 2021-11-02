package com.example.android.dogtionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.dogtionary.databinding.ViewItemBinding
import com.example.android.dogtionary.network.DogPhoto

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */

class DogPhotoGridAdapter(private val listener: Listener) :
    RecyclerView.Adapter<DogPhotoGridAdapter.DogPhotosViewHolder>() {
    private var dogPhotoList = mutableListOf<DogPhoto>()
    /**
     * The DogPhotoViewHolder constructor takes the binding variable from the associated
     * ViewItem, which nicely gives it access to the full [DogPhoto] information.
     */
    inner class DogPhotosViewHolder(var binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dogPhoto = binding.dogImageView
        init{
            dogPhoto.setOnClickListener {
                listener.onTaskClicked(absoluteAdapterPosition)
            }
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogPhotosViewHolder {
            return DogPhotosViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: DogPhotosViewHolder, position: Int) = with(holder){
            binding.photo = dogPhotoList[position]
        }

    companion object DiffCallback : DiffUtil.ItemCallback<DogPhoto>() {
        override fun areItemsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }
    }

    override fun getItemCount(): Int = dogPhotoList.size

    interface Listener{
        fun onTaskClicked(index: Int)
    }

}