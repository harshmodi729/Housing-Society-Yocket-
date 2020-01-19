package com.harsh.housingsocienty.ui.myunit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.data.local.AppDatabase
import com.harsh.housingsocienty.extension.isInternetAvailable
import com.harsh.housingsocienty.extension.makeContext
import com.harsh.housingsocienty.model.MyUnit
import kotlinx.android.synthetic.main.fragment_my_unit.*

class MyUnitFragment : Fragment(), IMyUnitView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_unit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val appDatabase = AppDatabase.getInstance(context!!)
        val presenter = MyUnitPresenter(this)

        progress.show()
        if (context!!.isInternetAvailable())
            presenter.getMyUnitData(appDatabase)
        else
            presenter.getMyUnitLocalDb(viewLifecycleOwner, appDatabase)
    }

    override fun showToast(message: String) {
        if (progress != null) {
            progress.hide()
            context!!.makeContext(message)
        }
    }

    override fun getMyUnitData(item: MyUnit) {
        if (progress != null) {
            progress.hide()
            tvDue.text = "${item.due}"
            tvNextDue.text = if (item.next_due == "") "Over Due" else "${item.due}"
            tvOpen.text = "${item.open_complaint}"
            tvClosed.text = "${item.closed_complaint}"
            tvTotalMember.text = "${item.total_members}"
            tvLeasesDetail.text = "${item.leases_detail}"
        }
    }
}
