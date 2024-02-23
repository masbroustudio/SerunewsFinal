package com.serunews.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_indo")
data class NewsIndoEntity(

    @ColumnInfo("aksesurl")
    var aksesurl: String,

    @PrimaryKey
    @ColumnInfo("judul")
    var judul: String,

    @ColumnInfo("foto")
    var foto: String,

    @ColumnInfo("headline")
    var headline: String,

    @ColumnInfo("kategori")
    var kategori: String,

    @ColumnInfo("rilisberita")
    var rilisberita: String,

    @ColumnInfo("news_badge")
    var news_badge: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)