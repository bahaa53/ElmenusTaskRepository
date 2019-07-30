package com.rocca.umrah.elmenustask.domain.repository

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.TagsElementsResponse

interface TagsRepository {
     suspend fun getElmenusTags(page: Int): LiveData<MutableList<Tag?>>
}