package com.harsh.housingsocienty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.housingsocienty.model.UpcomingEvents

@Dao
interface UpcomingEventsDao {

    @Query("SELECT * FROM upcoming_events")
    fun getAllUpcomingEventsList(): LiveData<List<UpcomingEvents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingEventsList(repoItem: UpcomingEvents)

    @Query("DELETE FROM upcoming_events")
    suspend fun deleteAllUpcomingEvents()
}