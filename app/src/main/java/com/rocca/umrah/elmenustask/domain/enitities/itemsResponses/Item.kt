package com.rocca.umrah.elmenustask.domain.enitities.itemsResponses


import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "elmenus_items")
data class Item(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    @PrimaryKey
    @NonNull
    val name: String,
    @SerializedName("photoUrl")
    val photoUrl: String?
) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(photoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}