package com.serunews.core.source.local

import com.serunews.core.source.local.entity.NewsIndoEntity
import com.serunews.core.source.local.room.IndoNewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val indoNewsDao: IndoNewsDao) {

    fun getAllNewsTech(): Flow<List<NewsIndoEntity>> = indoNewsDao.getAllNewsTech()

    fun getFavoriteNewsTech(): Flow<List<NewsIndoEntity>> = indoNewsDao.getFavoriteNewsTech()

    suspend fun insertNewsTech(newsTechList: List<NewsIndoEntity>) =
        indoNewsDao.insertNewsTech(newsTechList)

    fun setFavoriteNewsTech(newsTech: NewsIndoEntity, newState: Boolean) {
        newsTech.isFavorite = newState
        indoNewsDao.updateFavoriteNewsTech(newsTech)
    }
}