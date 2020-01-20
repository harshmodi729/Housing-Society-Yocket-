package com.harsh.housingsocienty.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.adapter.UpcomingEventsAdapter
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.getAppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeContext
import com.harsh.housingsocienty.model.UpcomingEvents
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), IDashboardView {
    private lateinit var adapter: UpcomingEventsAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val presenter = DashboardPresenter(this)
        appDatabase = context!!.getAppDatabase()
        adapter = UpcomingEventsAdapter(context!!)
        rvUpcomingEvents.layoutManager = LinearLayoutManager(context!!)
        rvUpcomingEvents.adapter = adapter

        progress.show()
        getAllUpcomingEventsLocalDb()
        if (context!!.isInternetAvailable())
            presenter.getAllUpcomingEvents(appDatabase)
    }

    private fun getAllUpcomingEventsLocalDb() {
        appDatabase.getUpcomingEventDao().getAllUpcomingEventsList()
            .observe(viewLifecycleOwner,
                Observer<List<UpcomingEvents>?> { response ->
                    if (progress != null) {
                        progress.hide()
                        response?.let { adapter.addData(it as ArrayList<UpcomingEvents>) }
                    }
                })
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeContext(message)
        }
    }
}