package com.harsh.housingsocienty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.model.Forum
import kotlinx.android.synthetic.main.lay_forum_list.view.*
import kotlinx.android.synthetic.main.lay_upcoming_list.view.tvDesc
import kotlinx.android.synthetic.main.lay_upcoming_list.view.tvTitle

class ForumAdapter(private val mContext: Context) :
    RecyclerView.Adapter<ForumAdapter.ViewHolder>() {
    private var alForum = ArrayList<Forum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.lay_forum_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = alForum.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alForum[position]
        holder.bindView(item)
    }

    fun addData(forums: ArrayList<Forum>) {
        alForum = forums
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: Forum) {
            view.tvTitle.text = item.title
            view.tvDesc.text = item.description
            view.tvTopics.text = item.total_topics.toString().plus(" Topics")
        }
    }
}