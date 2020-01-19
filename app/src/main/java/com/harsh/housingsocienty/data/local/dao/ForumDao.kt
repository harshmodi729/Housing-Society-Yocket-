package com.harsh.housingsocienty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.housingsocienty.model.Forum

@Dao
interface ForumDao {

    @Query("SELECT * FROM forum")
    fun getAllForumList(): LiveData<List<Forum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForumList(forumData: Forum)

    @Query("DELETE FROM forum")
    suspend fun deleteAllForum()
}