package com.maksimzotov.notebook.domain.usecases.about

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsAboutUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getItemsAbout(): Flow<Response<List<ItemAbout>>> = repository.itemsAbout
}