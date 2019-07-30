package com.rocca.umrah.elmenustask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocca.umrah.elmenustask.data.database.dao.ItemsDao
import com.rocca.umrah.elmenustask.data.database.dao.TagsDao
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

@Database(
    entities = [Tag::class, Item::class],
    version = 1
)
abstract class ElmenusDatabase : RoomDatabase() {

    abstract fun ItemsDao(): ItemsDao
    abstract fun TagsDao(): TagsDao

    companion object {
        @Volatile private var instance: ElmenusDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }


        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ElmenusDatabase::class.java, "elmenuesDatabase.db"
            ).build()

    }
}