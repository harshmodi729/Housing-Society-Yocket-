package com.harsh.housingsocienty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
                .into(view.ivEvents)
        }
    }
}