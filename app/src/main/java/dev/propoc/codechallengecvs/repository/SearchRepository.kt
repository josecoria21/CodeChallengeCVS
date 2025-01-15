package dev.propoc.codechallengecvs.repository

import dev.propoc.codechallengecvs.model.Item
import dev.propoc.codechallengecvs.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun search(query: String): Flow<List<Item>> = flow {
        val response = apiService.fetchData(name = query)
        emit(response.items)
    }
}
