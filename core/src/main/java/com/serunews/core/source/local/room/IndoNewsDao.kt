package com.serunews.core.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.serunews.core.source.local.entity.NewsIndoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IndoNewsDao {

    @Query("SELECT * FROM news_indo")
    fun getAllNewsTech(): Flow<List<NewsIndoEntity>>

    @Query("SELECT * FROM news_indo where isFavorite = 1")
    fun getFavoriteNewsTech(): Flow<List<NewsIndoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsTech(newsTech: List<NewsIndoEntity>)

    @Update
    fun updateFavoriteNewsTech(newsTech: NewsIndoEntity)
}