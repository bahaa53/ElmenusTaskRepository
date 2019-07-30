package com.rocca.umrah.elmenustask.domain.enitities.itemsResponses


import com.google.gson.annotations.SerializedName

data class ItemsElementsResponse(
    @SerializedName("items")
    val items: MutableList<Item?>
)