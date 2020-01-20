package com.harsh.housingsocienty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.housingsocienty.model.ImagesItem

@Dao
interface MoreImageDao {

    @Query("SELECT * FROM images")
    fun getMoreImageList(): LiveData<List<ImagesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoreImage(moreImage: ArrayList<ImagesItem>)

    @Query("DELETE FROM images")
    fun deleteMoreImage()
}