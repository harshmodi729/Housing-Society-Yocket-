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
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.model.UpcomingEvents
import kotlinx.android.synthetic.main.lay_upcoming_list.view.*

class UpcomingEventsAdapter(private val mContext: Context) :
    RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder>() {
    private var alUpcomingEvents = ArrayList<UpcomingEvents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.lay_upcoming_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = alUpcomingEvents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alUpcomingEvents[position]
        holder.bindView(item)
    }

    fun addData(upcomingEvents: ArrayList<UpcomingEvents>) {
        alUpcomingEvents = upcomingEvents
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: UpcomingEvents) {
            view.tvTitle.text = item.title
            view.tvDate.text = item.date
            view.tvDesc.text = item.description
            Glide.with(mContext)
                .load(item.image_url)
                .centerCrop()
                .transform(CenterInside(), RoundedCorners(16))
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.progress.visibility = View.GONE
                        view.ivEvents.setBackgroundResource(R.drawable.placeholder)
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
                .into(view.ivEvents)
        }
    }
}