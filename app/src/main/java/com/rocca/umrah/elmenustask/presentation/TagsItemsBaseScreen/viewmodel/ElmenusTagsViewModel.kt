package com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.domain.usecase.ElmenusItemsUseCase
import com.rocca.umrah.elmenustask.domain.usecase.GetElmenusTagsUseCase


class ElmenusTagsViewModel(
    private val elmenusTagsUseCase: GetElmenusTagsUseCase,
    private val elmenusItemsUseCase: ElmenusItemsUseCase
) : ViewModel() {



    suspend fun requestElmenusTags(page: Int): LiveData<MutableList<Tag?>> {
        val params = GetElmenusTagsUseCase.Params(page)
        val elmenusTagsresult = elmenusTagsUseCase.getElmenusTags(params);
        return elmenusTagsresult;
    }

    suspend fun requestElmenusItemsOfSpecificTag(tagName: String?): LiveData<MutableList<Item?>> {
        val params = ElmenusItemsUseCase.Params(tagName)
        val elmenusItemsresult = elmenusItemsUseCase.getElmenusItemsOfSpecificTag(params);
        return elmenusItemsresult;
    }
}
