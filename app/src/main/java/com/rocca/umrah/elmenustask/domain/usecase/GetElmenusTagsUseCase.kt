package com.rocca.umrah.elmenustask.domain.usecase

import androidx.lifecycle.LiveData
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.domain.repository.TagsRepository

class GetElmenusTagsUseCase(private val tagsRepository: TagsRepository) {

    suspend fun getElmenusTags(params: Params): LiveData<MutableList<Tag?>> {
        return with(params) {
            tagsRepository.getElmenusTags(page)
        }
    }

    class Params(
        val page: Int
    )
}