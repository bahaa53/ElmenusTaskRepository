package com.rocca.umrah.elmenustask.domain.repository

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

interface ElmenusItemsRepository {
    suspend fun getElmenusItemsOfSpecificTag(tagName: String?): LiveData<MutableList<Item?>>
}