package com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rocca.umrah.elmenustask.domain.usecase.ElmenusItemsUseCase
import com.rocca.umrah.elmenustask.domain.usecase.GetElmenusTagsUseCase

class ElmenusTagsViewModelFactory(
    private val getElmenusTagsUseCase: GetElmenusTagsUseCase,
    private val elmenusItemsUseCase: ElmenusItemsUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElmenusTagsViewModel::class.java)) {
            return ElmenusTagsViewModel(getElmenusTagsUseCase, elmenusItemsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}