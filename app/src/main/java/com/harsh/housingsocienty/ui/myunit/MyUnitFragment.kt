package com.harsh.housingsocienty.ui.myunit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.getAppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeToast
import com.harsh.housingsocienty.model.MyUnit
import kotlinx.android.synthetic.main.fragment_my_unit.*

class MyUnitFragment : Fragment(), IMyUnitView {
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_unit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appDatabase = context!!.getAppDatabase()
        val presenter = MyUnitPresenter(this)

        progress.show()
        getMyUnitLocalDb()
        if (context!!.isInternetAvailable())
            presenter.getMyUnitData(appDatabase)
    }

    private fun getMyUnitLocalDb() {
        appDatabase.getMyUnitDao().getMyUnitData()
            .observe(viewLifecycleOwner,
                Observer<MyUnit?> { response ->
                    if (progress != null) {
                        progress.hide()
                        response?.let { setMyUnitData(response) }
                            ?: context!!.makeToast(getString(R.string.no_data_found))
                    }
                })
    }

    private fun setMyUnitData(item: MyUnit) {
        tvDue.text = "${item.due}"
        tvNextDue.text =
            if (item.next_due == "") getString(R.string.over_due) else "${item.due}"
        tvOpen.text = "${item.open_complaint}"
        tvClosed.text = "${item.closed_complaint}"
        tvTotalMember.text = "${item.total_members}"
        tvLeasesDetail.text = "${item.leases_detail}"
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeToast(message)
        }
    }
}
