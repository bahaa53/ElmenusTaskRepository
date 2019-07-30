package com.rocca.umrah.elmenustask.domain.usecase

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.repository.ElmenusItemsRepository


class ElmenusItemsUseCase(private val itemsRepository: ElmenusItemsRepository) {

    suspend fun getElmenusItemsOfSpecificTag(params: Params): LiveData<MutableList<Item?>> {
        return with(params) {
            itemsRepository.getElmenusItemsOfSpecificTag(tagName)
        }
    }

    class Params(
        val tagName: String?
    )
}