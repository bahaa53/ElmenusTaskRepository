package com.rocca.umrah.elmenustask.data.api.elmenusItemsNetworkDataSource

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.data.api.BaseNetworkDataSource
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.ItemsElementsResponse
import retrofit2.Response

interface ElmenusItemsNetworkDataSource : BaseNetworkDataSource {

    val downloadElmenusItemsOfSpecificTag: LiveData<MutableList<Item?>>

    suspend fun getElmenusItemElements(
        tagName: String?
    )
}