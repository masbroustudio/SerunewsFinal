package com.serunews.core.domain.repository

import com.serunews.core.domain.model.IndoNews
import kotlinx.coroutines.flow.Flow

interface INewsIndoRepository {

    fun getAllNewsTech(): Flow<com.serunews.core.source.Resource<List<IndoNews>>>

    fun getFavoriteNewsTech(): Flow<List<IndoNews>>

    fun setFavoriteNewsTech(indoNews: IndoNews, state: Boolean)
}