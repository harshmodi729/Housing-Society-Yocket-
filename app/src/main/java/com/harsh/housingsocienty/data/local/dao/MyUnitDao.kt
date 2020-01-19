package com.harsh.housingsocienty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.housingsocienty.model.MyUnit

@Dao
interface MyUnitDao {

    @Query("SELECT * FROM my_unit")
    fun getMyUnitData(): LiveData<MyUnit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyUnitData(myUnit: MyUnit)

    @Query("DELETE FROM my_unit")
    suspend fun deleteMyUnit()
}