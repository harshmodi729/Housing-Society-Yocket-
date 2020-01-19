package com.harsh.housingsocienty.ui.forums

import com.harsh.housingsocienty.model.Forum

interface IForumView {

    fun showToast(message: String)
    fun getForumList(alForum: ArrayList<Forum>)
}