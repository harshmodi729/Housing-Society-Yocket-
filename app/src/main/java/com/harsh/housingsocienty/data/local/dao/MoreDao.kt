package com.harsh.housingsocienty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.housingsocienty.model.More

@Dao
interface MoreDao {

    @Query("SELECT * FROM more")
    fun getMoreData(): LiveData<More>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoreData(moreData: More)

    @Query("DELETE FROM more")
    fun deleteMore()
}