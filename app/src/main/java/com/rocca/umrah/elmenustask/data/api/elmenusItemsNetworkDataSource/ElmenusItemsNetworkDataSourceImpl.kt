package com.rocca.umrah.elmenustask.data.api.elmenusItemsNetworkDataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rocca.umrah.elmenustask.data.api.ApiInterface
import com.rocca.umrah.elmenustask.data.api.checkingConnectivity.NoConnectivityException
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.ItemsElementsResponse
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.TagsElementsResponse
import com.rocca.umrah.elmenustask.domain.repository.BaseRepository
import retrofit2.Response

private const val ERROR_MESSAGE_EXECPTION = "Error Fetching Tags Items "

class ElmenusItemsNetworkDataSourceImpl(private val apiInterface: ApiInterface) :
    ElmenusItemsNetworkDataSource, BaseRepository() {

    private val _downloadElmenusItemsOfSpecificTag = MutableLiveData<MutableList<Item?>>()

    override val downloadElmenusItemsOfSpecificTag: LiveData<MutableList<Item?>>
        get() = _downloadElmenusItemsOfSpecificTag

    override suspend fun getElmenusItemElements(tagName: String?) {
        try {
            val fetchElmenusItemsOfSpecificTag = apiInterface.getElmenusItems(tagName)
                .await()
            checkIfValidRespons(fetchElmenusItemsOfSpecificTag)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun <T : Any> checkIfValidRespons(fetchElmenusItemOfSpecificTag: Response<T>) {
        val fetchElmenusItemaOfSpecificTag = safeApiCall(call = {
            fetchElmenusItemOfSpecificTag as Response<ItemsElementsResponse>
        }, errorMessage = ERROR_MESSAGE_EXECPTION)
        _downloadElmenusItemsOfSpecificTag.postValue(fetchElmenusItemaOfSpecificTag?.items)
    }
}