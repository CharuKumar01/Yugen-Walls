package com.example.yugenwalls

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yugenwalls.Data.RandomAnimeImage
import com.example.yugenwalls.databinding.ItemImageBinding

class ImageAdapter : ListAdapter<RandomAnimeImage, ImageAdapter.ImageViewHolder>(
    ImageDiffCallBack()
) {
    inner class ImageViewHolder(val bind: ItemImageBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentAnimeImage = getItem(position)
        holder.bind.apply {
            Glide.with(ivAnimeImage).load(currentAnimeImage.url).into(ivAnimeImage)
        }
    }
}

class ImageDiffCallBack : DiffUtil.ItemCallback<RandomAnimeImage>() {
    override fun areItemsTheSame(oldItem: RandomAnimeImage, newItem: RandomAnimeImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RandomAnimeImage, newItem: RandomAnimeImage): Boolean {
        return oldItem == newItem
    }

}