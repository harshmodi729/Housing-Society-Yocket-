package com.harsh.housingsocienty.ui.forums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.adapter.ForumAdapter
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.getAppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeToast
import com.harsh.housingsocienty.model.Forum
import kotlinx.android.synthetic.main.fragment_forum.*

class ForumFragment : Fragment(), IForumView {
    private lateinit var adapter: ForumAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val presenter = ForumPresenter(this)
        appDatabase = context!!.getAppDatabase()
        adapter = ForumAdapter(context!!)
        rvForum.layoutManager = LinearLayoutManager(context!!)
        rvForum.adapter = adapter

        progress.show()
        getAllForumsLocalDb()
        if (context!!.isInternetAvailable())
            presenter.getAllForums(appDatabase)
    }

    private fun getAllForumsLocalDb() {
        appDatabase.getForumDao().getAllForumList()
            .observe(viewLifecycleOwner,
                Observer<List<Forum>> { response ->
                    if (progress != null) {
                        progress.hide()
                        response?.let {
                            if (response.isNotEmpty()) {
                                tvNoData.visibility = View.GONE
                                adapter.addData(response as ArrayList<Forum>)
                            } else tvNoData.visibility = View.VISIBLE
                        } ?: kotlin.run { tvNoData.visibility = View.VISIBLE }
                    }
                })
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeToast(message)
        }
    }
}