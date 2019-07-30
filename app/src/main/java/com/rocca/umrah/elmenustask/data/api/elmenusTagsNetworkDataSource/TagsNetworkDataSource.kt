package com.rocca.umrah.elmenustask.data.api.elmenusTagsNetworkDataSource

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.data.api.BaseNetworkDataSource
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

interface TagsNetworkDataSource : BaseNetworkDataSource{

    val downloadElmenusTagsElements: LiveData<MutableList<Tag?>>

    suspend fun getElmenusTagsElements(
        page: Int = 0
    )
}