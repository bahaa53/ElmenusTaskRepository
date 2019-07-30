package com.rocca.umrah.elmenustask.data.repositories

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.data.api.elmenusItemsNetworkDataSource.ElmenusItemsNetworkDataSource
import com.rocca.umrah.elmenustask.data.database.dao.ItemsDao
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.repository.ElmenusItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ElmenusItemsRepositoryImpl(
    private val itemsDao: ItemsDao
    , private val itemsNetworkDataSource: ElmenusItemsNetworkDataSource
) : ElmenusItemsRepository {

    init {
        itemsNetworkDataSource.apply {
            downloadElmenusItemsOfSpecificTag.observeForever { response ->
                if (response != null) {
                    persistElMenusItemsOfSpecificTag(response)
                }
            }
        }
    }

    private fun persistElMenusItemsOfSpecificTag(response: List<Item?>?) {
        GlobalScope.launch(Dispatchers.IO) {
            itemsDao.upsertElmenusItemsOfSpecificTag(response)
        }
    }

    override suspend fun getElmenusItemsOfSpecificTag(tagName: String?): LiveData<MutableList<Item?>> {
        return withContext(Dispatchers.IO) {
            fetchElMenusItemsOfSpecificTag(tagName)
            return@withContext itemsDao.getAllElmenusItems(tagName)
        }
    }

    private suspend fun fetchElMenusItemsOfSpecificTag(tagName: String?) {
        itemsNetworkDataSource.getElmenusItemElements(tagName)
    }
}