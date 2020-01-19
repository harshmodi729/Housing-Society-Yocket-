package com.harsh.housingsocienty.ui.dashboard

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.firebase.database.*
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.model.UpcomingEvents
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardPresenter(internal var mIDashboardView: IDashboardView) {

    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseInstance: FirebaseDatabase
    var alUpcomingEvents = ArrayList<UpcomingEvents>()

    fun getAllUpcomingEvents(appDatabase: AppDatabase) {
        firebaseInstance = FirebaseDatabase.getInstance()
        firebaseDatabase = firebaseInstance.reference
        val upcomingEvents = firebaseDatabase.child("upcoming_events")
        upcomingEvents.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                mIDashboardView.showToast(error.message)
            }

            override fun onDataChange(response: DataSnapshot) {
                val lists = response.value as ArrayList<*>
                for (i in 0 until lists.size) {
                    val item = response.child(i.toString()).getValue(UpcomingEvents::class.java)
                    item?.let {
                        GlobalScope.launch {
                            appDatabase.getUpcomingEventDao().insertUpcomingEventsList(item)
                        }
                        alUpcomingEvents.add(item)
                    }
                }
                mIDashboardView.getUpcomingList(alUpcomingEvents)
            }
        })
    }

    fun getAllUpcomingEventsLocalDb(viewLifecycleOwner: LifecycleOwner, appDatabase: AppDatabase) {
        appDatabase.getUpcomingEventDao().getAllUpcomingEventsList()
            .observe(viewLifecycleOwner,
                Observer<List<UpcomingEvents>?> { response ->
                    alUpcomingEvents = response as ArrayList<UpcomingEvents>
                    mIDashboardView.getUpcomingList(alUpcomingEvents)
                })
    }
}