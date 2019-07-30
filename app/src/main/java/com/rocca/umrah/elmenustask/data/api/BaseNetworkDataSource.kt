package com.rocca.umrah.elmenustask.data.api

import retrofit2.Response

interface BaseNetworkDataSource {
    suspend fun <T : Any> checkIfValidRespons(baseResponse: Response<T>)
}