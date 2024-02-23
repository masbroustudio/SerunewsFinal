package com.serunews.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serunews.core.source.local.entity.NewsIndoEntity


@Database(entities = [NewsIndoEntity::class], version = 1, exportSchema = false)
abstract class IndoNewsDatabase : RoomDatabase() {

    abstract fun indoNewsDao(): IndoNewsDao

}