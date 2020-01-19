package com.harsh.housingsocienty.ui.myunit

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.firebase.database.*
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.model.MyUnit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyUnitPresenter(internal var mIMyUnitView: IMyUnitView) {

    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseInstance: FirebaseDatabase

    fun getMyUnitData(appDatabase: AppDatabase) {
        firebaseInstance = FirebaseDatabase.getInstance()
        firebaseDatabase = firebaseInstance.reference
        val upcomingEvents = firebaseDatabase.child("my_unit")
        upcomingEvents.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                mIMyUnitView.showToast(error.message)
            }

            override fun onDataChange(response: DataSnapshot) {
                val item = response.getValue(MyUnit::class.java)
                item?.let {
                    GlobalScope.launch { appDatabase.getMyUnitDao().insertMyUnitData(item) }
                    mIMyUnitView.getMyUnitData(item)
                }
            }
        })
    }

    fun getMyUnitLocalDb(viewLifecycleOwner: LifecycleOwner, appDatabase: AppDatabase) {
        appDatabase.getMyUnitDao().getMyUnitData()
            .observe(viewLifecycleOwner,
                Observer<MyUnit> { response -> mIMyUnitView.getMyUnitData(response) })
    }
}