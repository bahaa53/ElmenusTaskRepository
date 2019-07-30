package com.rocca.umrah.elmenustask.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsertElmenusItemsOfSpecificTag(elmenusItems: List<Item?>?)

    @Query("select * from elmenus_items where name like :tagName  || '%' ")
    abstract fun getAllElmenusItems(tagName: String?): LiveData<MutableList<Item?>>
}