package com.example.android.dogtionary.chapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import coil.load
import com.example.android.dogtionary.databinding.FragmentFavoriteLayoutBinding


class FavoriteListAdapter(
    private val listener: Listener, private val photoList: List<String>
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentFavoriteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = photoList.size

    inner class ViewHolder(binding: FragmentFavoriteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val favoriteImageView: ImageView = binding.favoritesImageView
        private val favoriteIcon: ImageButton = binding.addToFavIcon

        fun bind(dogPhoto: String) {
            favoriteImageView.load(dogPhoto)
        }

        init {
            favoriteIcon.setOnClickListener {
                listener.onFavButtonClicked(favoriteIcon, absoluteAdapterPosition)
            }
        }
    }

    interface Listener {
        fun onFavButtonClicked(view: ImageButton, index: Int) {}
    }
}