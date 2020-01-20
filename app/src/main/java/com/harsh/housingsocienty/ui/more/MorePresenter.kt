package com.harsh.housingsocienty.ui.more

import com.google.firebase.database.*
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.model.More
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MorePresenter(internal var mIMoreView: IMoreView) {

    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseInstance: FirebaseDatabase

    fun getMoreData(appDatabase: AppDatabase) {
        firebaseInstance = FirebaseDatabase.getInstance()
        firebaseDatabase = firebaseInstance.reference
        val upcomingEvents = firebaseDatabase.child("more")
        upcomingEvents.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                mIMoreView.showToast(error.message)
            }

            override fun onDataChange(response: DataSnapshot) {
                val item = response.getValue(More::class.java)
                item?.let {
                    GlobalScope.launch { appDatabase.getMoreDao().insertMoreData(item) }
                    val imageDao = appDatabase.getMoreImageDao()
                    GlobalScope.launch { imageDao.insertMoreImage(item.images) }
                }
            }
        })
    }
}