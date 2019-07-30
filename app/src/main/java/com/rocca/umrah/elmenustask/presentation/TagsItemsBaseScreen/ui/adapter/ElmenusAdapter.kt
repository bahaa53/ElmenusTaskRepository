package com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.holders.ElmenusTagsViewHolder

class ElmenusAdapter<T : Any>(private val elmenusList: List<T?>, val clickListener: (T?) -> Unit) :
    RecyclerView.Adapter<ElmenusTagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElmenusTagsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ElmenusTagsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = elmenusList?.size

    override fun onBindViewHolder(holder: ElmenusTagsViewHolder, position: Int) {
        val tag: T? = elmenusList?.get(position)
        holder.bindData(tag, clickListener)
    }
}