package com.rocca.umrah.elmenustask.data.repositories

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.data.api.elmenusTagsNetworkDataSource.TagsNetworkDataSource
import com.rocca.umrah.elmenustask.data.database.dao.TagsDao
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.domain.repository.TagsRepository
import kotlinx.coroutines.*

class TagsRepostoryImp(
    private val tagsDao: TagsDao
    , private val tagsNetworkDataSource: TagsNetworkDataSource
) : TagsRepository {

    init {
        tagsNetworkDataSource.apply {
            downloadElmenusTagsElements.observeForever { response ->
                if (response != null) {
                    persistElMenusTags(response)
                }
            }
        }
    }

    private fun persistElMenusTags(response: List<Tag?>?) {
        GlobalScope.launch(Dispatchers.IO) {
            tagsDao.upsertElmenusTags(response)
        }
    }

    override suspend fun getElmenusTags(page: Int): LiveData<MutableList<Tag?>> {
        return withContext(Dispatchers.IO) {
            fetchElMenusTags(page)
            return@withContext tagsDao.getAllElmenusTags()
        }
    }

    private suspend fun fetchElMenusTags(page:Int) {
        tagsNetworkDataSource.getElmenusTagsElements(page)
    }
}