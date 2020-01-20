package com.harsh.housingsocienty.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.model.ImagesItem
import kotlinx.android.synthetic.main.lay_more_image_list.view.*

class MoreImageAdapter(
    private val mContext: Context,
    val onImageClickListener: OnImageClickListener
) :
    RecyclerView.Adapter<MoreImageAdapter.ViewHolder>() {
    private var alImageList = ArrayList<ImagesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.lay_more_image_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = alImageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alImageList[position]
        holder.bindView(item)
    }

    fun addData(imageList: ArrayList<ImagesItem>) {
        alImageList = imageList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: ImagesItem) {
            Glide.with(mContext)
                .load(item.image_url)
                .centerCrop()
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.progress.visibility = View.GONE
                        view.ivImage.setBackgroundResource(R.drawable.placeholder)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.progress.visibility = View.GONE
                        return false
                    }
                })
                .into(view.ivImage)

            view.ivImage.setOnClickListener {
                onImageClickListener.onImageClick(item)
            }
        }
    }

    interface OnImageClickListener {
        fun onImageClick(item: ImagesItem)
    }
}