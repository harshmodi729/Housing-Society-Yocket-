package com.harsh.housingsocienty.ui.forums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.adapter.ForumAdapter
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeContext
import com.harsh.housingsocienty.model.Forum
import kotlinx.android.synthetic.main.fragment_forum.*

class ForumFragment : Fragment(), IForumView {
    private lateinit var adapter: ForumAdapter

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
        val appDatabase = AppDatabase.getInstance(context!!)
        adapter = ForumAdapter(context!!)
        rvForum.layoutManager = LinearLayoutManager(context!!)
        rvForum.adapter = adapter

        progress.show()
        if (context!!.isInternetAvailable())
            presenter.getAllForums(appDatabase)
        else
            presenter.getAllForumsLocalDb(viewLifecycleOwner, appDatabase)
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeContext(message)
        }
    }

    override fun getForumList(alForum: ArrayList<Forum>) {
        if (progress != null) {
            progress.hide()
            adapter.addData(alForum)
        }
    }
}