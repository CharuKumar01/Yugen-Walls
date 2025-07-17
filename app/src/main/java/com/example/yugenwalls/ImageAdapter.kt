package com.example.yugenwalls

import android.media.Image
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yugenwalls.databinding.ItemImageBinding

class ImagesAdapter() : ListAdapter<Image, ImagesAdapter.ImageViewHolder {
    inner class ImageViewHolder(val bind: ItemImageBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter {

    }

    override fun onBindViewHolder(holder: ImagesAdapter, position: Int) {

    }
}