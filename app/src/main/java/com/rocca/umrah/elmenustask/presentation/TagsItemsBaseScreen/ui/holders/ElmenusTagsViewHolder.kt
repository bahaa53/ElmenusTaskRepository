package com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rocca.umrah.elmenustask.R
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.squareup.picasso.Picasso

class ElmenusTagsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_elmenus_tags_layout, parent, false)) {
    private var mTagTitleView: TextView? = null
    private var mTagImageView: ImageView? = null

    init {
        mTagTitleView = itemView.findViewById(R.id.tv_tag_name)
        mTagImageView = itemView.findViewById(R.id.iv_tag)
    }

    fun <T : Any> bindData(genericItem: T?, clickListener: (T?) -> Unit) {
        when (genericItem) {
            is Item -> {
                setItemDataIntoUi(genericItem)
            }
            is Tag -> {
                setTagDataintoUi(genericItem)
            }
        }
        itemView.setOnClickListener { clickListener(genericItem) }

    }

    private fun setItemDataIntoUi(elmenusItem: Item?) {
        mTagTitleView?.text = elmenusItem?.name
        setPictureIntoUi(elmenusItem?.photoUrl)
    }

    private fun setTagDataintoUi(tag: Tag) {
        mTagTitleView?.text = tag?.tagName
        setPictureIntoUi(tag?.photoURL)
    }

    private fun setPictureIntoUi(photoURL: String?) {
        Picasso.get()
            .load(photoURL)
            .into(mTagImageView)
    }
}