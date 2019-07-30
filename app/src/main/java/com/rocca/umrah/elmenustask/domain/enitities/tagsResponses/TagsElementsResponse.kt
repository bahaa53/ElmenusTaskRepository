package com.rocca.umrah.elmenustask.domain.enitities.tagsResponses


import com.google.gson.annotations.SerializedName
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag

data class TagsElementsResponse(
    @SerializedName("tags")
    val tags: MutableList<Tag?>
)