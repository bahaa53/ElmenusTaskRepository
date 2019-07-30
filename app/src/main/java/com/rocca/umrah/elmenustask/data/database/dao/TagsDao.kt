package com.rocca.umrah.elmenustask.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

@Dao
interface TagsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertElmenusTags(elmenusTags: List<Tag?>?)

    @Query("select * from elmenus_tags")
    fun getAllElmenusTags(): LiveData<MutableList<Tag?>>
}