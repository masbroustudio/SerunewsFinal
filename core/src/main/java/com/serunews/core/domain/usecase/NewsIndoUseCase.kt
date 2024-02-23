package com.serunews.core.domain.usecase


import com.serunews.core.domain.model.IndoNews
import kotlinx.coroutines.flow.Flow

interface NewsIndoUseCase {

    fun getAllNewsIndo(): Flow<com.serunews.core.source.Resource<List<IndoNews>>>

    fun getFavoriteNewsTech(): Flow<List<IndoNews>>

    fun setFavoriteNewsIndo(indoNews: IndoNews, state: Boolean)

}