package com.serunews.core.utils

import com.serunews.core.domain.model.IndoNews
import com.serunews.core.source.local.entity.NewsIndoEntity
import com.serunews.core.source.remote.response.PostsItem


object DataMapper {
    fun mapResponsesToEntities(input: List<PostsItem>): List<NewsIndoEntity> {
        val newsTechList = ArrayList<NewsIndoEntity>()
        input.map {
            val newsTech = NewsIndoEntity(
                aksesurl = it.aksesurl,
                judul = it.judul,
                foto = it.foto,
                headline = it.headline,
                kategori = it.kategori,
                rilisberita = it.rilisberita,
                news_badge = it.news_badge,
                isFavorite = false
            )
            newsTechList.add(newsTech)
        }
        return newsTechList
    }

    fun mapEntitiesToDomain(input: List<NewsIndoEntity>): List<IndoNews> =
        input.map {
            IndoNews(
                aksesurl = it.aksesurl,
                judul = it.judul,
                foto = it.foto,
                headline = it.headline,
                kategori = it.kategori,
                rilisberita = it.rilisberita,
                news_badge = it.news_badge,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: IndoNews) = NewsIndoEntity(
        aksesurl = input.aksesurl,
        judul = input.judul,
        foto = input.foto,
        headline = input.headline,
        kategori = input.kategori,
        rilisberita = input.rilisberita,
        news_badge = input.news_badge,
        isFavorite = input.isFavorite
    )
}