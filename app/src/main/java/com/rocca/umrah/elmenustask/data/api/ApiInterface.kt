package com.rocca.umrah.elmenustask.data.api

import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.ItemsElementsResponse
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.TagsElementsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("tags/{page}")
    fun getElmenusTags(
        @Path(value = "page") page: Int = 0
    ): Deferred<Response<TagsElementsResponse>>

    @GET("items/{tagName}")
    fun getElmenusItems(
        @Path(value = "tagName") tagName: String?
    ): Deferred<Response<ItemsElementsResponse>>
}