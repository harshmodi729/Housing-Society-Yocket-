package com.harsh.housingsocienty.ui.myunit

import com.harsh.housingsocienty.model.MyUnit

interface IMyUnitView {

    fun showToast(message: String)
    fun getMyUnitData(item: MyUnit)
}