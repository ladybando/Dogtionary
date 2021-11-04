package com.example.android.dogtionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.dogtionary.databinding.DogViewLayoutBinding
import com.example.android.dogtionary.network.DogPhoto

class PhotoGridAdapter(private val listener: Listener) :
    ListAdapter<DogPhoto, PhotoGridAdapter.DogViewHolder>(DiffCallback) {
    /**
     * The DogViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [DogPhoto] information.
     */
    inner class DogViewHolder(private var binding: DogViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(DogPhoto: DogPhoto){
            binding.photo = DogPhoto
            binding.executePendingBindings()
        }

        private val dogPhotoImageView = binding.dogImageView
        init {
            dogPhotoImageView.setOnClickListener{
                listener.onImageClicked(absoluteAdapterPosition)
            }
        }
    }
    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int) : DogViewHolder{
        return DogViewHolder(DogViewLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }
    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dogPhoto = getItem(position)
        holder.bind(dogPhoto)
    }
    /**
     * Sets onClickListener for [DogsDisplayFragment]
     */
    interface Listener{
        fun onImageClicked(index: Int){ }
    }
    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [DogPhoto] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<DogPhoto>() {
        override fun areItemsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

    }
}
