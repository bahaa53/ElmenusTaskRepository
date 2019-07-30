package com.rocca.umrah.elmenustask.data.api.elmenusTagsNetworkDataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rocca.umrah.elmenustask.data.api.ApiInterface
import com.rocca.umrah.elmenustask.data.api.checkingConnectivity.NoConnectivityException
import com.rocca.umrah.elmenustask.domain.repository.BaseRepository
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.TagsElementsResponse
import retrofit2.Response

private const val ERROR_MESSAGE_EXECPTION = "Error Fetching Tags "

class TagsNetworkDataSourceImp(private val apiInterface: ApiInterface) : TagsNetworkDataSource, BaseRepository() {

    private val _downloadElmenusTagsElements = MutableLiveData<MutableList<Tag?>>()

    override val downloadElmenusTagsElements: LiveData<MutableList<Tag?>>
        get() = _downloadElmenusTagsElements

    override suspend fun getElmenusTagsElements(page: Int) {
        try {
            val fetchElmenusTags = apiInterface.getElmenusTags(page)
                .await()
            checkIfValidRespons(fetchElmenusTags)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun <T : Any> checkIfValidRespons(fetchElmenusTags: Response<T>) {
        val fetchElmenusTagsElements = safeApiCall(call = {
            fetchElmenusTags as Response<TagsElementsResponse>
        }, errorMessage = ERROR_MESSAGE_EXECPTION)
        _downloadElmenusTagsElements.postValue(fetchElmenusTagsElements?.tags)
    }
}
