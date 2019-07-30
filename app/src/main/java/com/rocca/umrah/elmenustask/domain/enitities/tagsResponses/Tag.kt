package com.rocca.umrah.elmenustask.domain.enitities.tagsResponses


import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "elmenus_tags")
data class Tag(
    @SerializedName("photoURL")
    val photoURL: String?,
    @SerializedName("tagName")
    @PrimaryKey
    @NonNull
    val tagName: String
) {

}