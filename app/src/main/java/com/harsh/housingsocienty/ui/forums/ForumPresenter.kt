package com.harsh.housingsocienty.ui.forums

import com.google.firebase.database.*
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.model.Forum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ForumPresenter(internal var mIForumView: IForumView) {

    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseInstance: FirebaseDatabase
    var alForum = ArrayList<Forum>()

    fun getAllForums(appDatabase: AppDatabase) {
        firebaseInstance = FirebaseDatabase.getInstance()
        firebaseDatabase = firebaseInstance.reference
        val upcomingEvents = firebaseDatabase.child("forum")
        upcomingEvents.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                mIForumView.showToast(error.message)
            }

            override fun onDataChange(response: DataSnapshot) {
                val lists = response.value as ArrayList<*>
                for (i in 0 until lists.size) {
                    val item = response.child(i.toString()).getValue(Forum::class.java)
                    item?.let {
                        GlobalScope.launch { appDatabase.getForumDao().insertForumList(item) }
                        alForum.add(item)
                    }
                }
            }
        })
    }
}