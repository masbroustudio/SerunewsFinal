package com.serunews.core.domain.usecase

import com.serunews.core.domain.model.IndoNews
import com.serunews.core.domain.repository.INewsIndoRepository


class NewsIndoInteracator(private val newsIndoRepository: INewsIndoRepository): NewsIndoUseCase {

    override fun getAllNewsIndo() = newsIndoRepository.getAllNewsTech()

    override fun getFavoriteNewsTech() = newsIndoRepository.getFavoriteNewsTech()

    override fun setFavoriteNewsIndo(indoNews: IndoNews, state: Boolean) =
        newsIndoRepository.setFavoriteNewsTech(indoNews, state)

}