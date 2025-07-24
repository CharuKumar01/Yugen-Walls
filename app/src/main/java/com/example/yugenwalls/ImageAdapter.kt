package com.example.yugenwalls

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.yugenwalls.Data.RandomAnimeImage
import com.example.yugenwalls.databinding.ItemImageBinding

class ImageAdapter(private val context: Context) : ListAdapter<RandomAnimeImage, ImageAdapter.ImageViewHolder>(
    ImageDiffCallBack()
) {
    @SuppressLint("ClickableViewAccessibility")
    inner class ImageViewHolder(val bind: ItemImageBinding) : RecyclerView.ViewHolder(bind.root) {
        private val gestureDetectorImage = GestureDetector(bind.root.context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    bind.ivEmptyLike.visibility = View.GONE
                    bind.ivLike.visibility = View.VISIBLE
                    return true
                }
            })
        private val gestureDetectorEmptyLike = GestureDetector(bind.ivLike.context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    bind.ivEmptyLike.visibility = View.VISIBLE
                    bind.ivLike.visibility = View.GONE
                    return true
                }
            })
        private val gestureDetectorLike = GestureDetector(bind.ivEmptyLike.context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    bind.ivEmptyLike.visibility = View.GONE
                    bind.ivLike.visibility = View.VISIBLE
                    return true
                }
            })

        init {
            bind.ivAnimeImage.setOnTouchListener { _, event ->
                gestureDetectorImage.onTouchEvent(event)
                true
            }
            bind.ivLike.setOnTouchListener { _, event ->
                gestureDetectorLike.onTouchEvent(event)
                true
            }
            bind.ivEmptyLike.setOnTouchListener { _, event ->
                gestureDetectorEmptyLike.onTouchEvent(event)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentAnimeImage = getItem(position)
        holder.bind.apply {
            ivAnimeImage.setOnClickListener(null)
            val loadingPlaceholder =
                AppCompatResources.getDrawable(context, R.drawable.loading_placeholder) as AnimationDrawable

            loadingPlaceholder.apply {
                setEnterFadeDuration(10)
                setExitFadeDuration(300)
                start()
            }

            Glide.with(ivAnimeImage.context)
                .load(currentAnimeImage.url)
                .placeholder(loadingPlaceholder)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        ivEmptyLike.visibility = View.VISIBLE
                        return false
                    }
                })
                .into(ivAnimeImage)
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