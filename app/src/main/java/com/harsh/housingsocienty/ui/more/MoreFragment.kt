package com.harsh.housingsocienty.ui.more

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.adapter.MoreImageAdapter
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.getAppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeToast
import com.harsh.housingsocienty.model.ImagesItem
import com.harsh.housingsocienty.model.More
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : Fragment(), IMoreView, MoreImageAdapter.OnImageClickListener {

    private lateinit var adapter: MoreImageAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val presenter = MorePresenter(this)
        appDatabase = context!!.getAppDatabase()
        adapter = MoreImageAdapter(context!!, this)
        rvPhotos.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        rvPhotos.adapter = adapter

        progress.show()
        getMoreDataLocalDb()
        getMoreImagesLocalDb()
        if (context!!.isInternetAvailable())
            presenter.getMoreData(appDatabase)

        btnClose.setOnClickListener {
            layFullImage.visibility = View.GONE
        }
    }

    private fun getMoreDataLocalDb() {
        appDatabase.getMoreDao().getMoreData()
            .observe(viewLifecycleOwner, Observer<More> {
                if (progress != null) {
                    progress.hide()
                    it?.let { setMoreData(it) }
                        ?: context!!.makeToast(getString(R.string.no_data_found))
                }
            })
    }

    private fun getMoreImagesLocalDb() {
        appDatabase.getMoreImageDao().getMoreImageList()
            .observe(viewLifecycleOwner, Observer<List<ImagesItem>> {
                it?.let { adapter.addData(it as ArrayList<ImagesItem>) }
            })
    }

    private fun setMoreData(item: More) {
        tvNotificationDetail.text = item.last_notification_text
        tvNoticeCount.text = "${item.notification_count}"
        tvContacts.text = "${item.contacts}"
        tvStaff.text = "${item.staff}"
        tvNeighbours.text = "${item.neighbours}"
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeToast(message)
        }
    }

    override fun onImageClick(item: ImagesItem) {
        layFullImage.visibility = View.VISIBLE
        Glide.with(context!!)
            .load(item.image_url)
            .centerCrop()
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressFullImage.visibility = View.GONE
                    ivFullImage.setBackgroundResource(R.drawable.placeholder)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressFullImage.visibility = View.GONE
                    return false
                }
            })
            .into(ivFullImage)
    }
}