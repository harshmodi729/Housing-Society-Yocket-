package com.harsh.housingsocienty.ui.dashboard

import com.harsh.housingsocienty.model.UpcomingEvents

interface IDashboardView {

    fun showToast(message: String)
    fun getUpcomingList(alUpcomingEvents: ArrayList<UpcomingEvents>)
}