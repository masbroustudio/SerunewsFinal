package com.serunews.newsapp.detail

import androidx.lifecycle.ViewModel
import com.serunews.core.domain.model.IndoNews
import com.serunews.core.domain.usecase.NewsIndoUseCase

class DetailViewModel(private val newsIndoUseCase: NewsIndoUseCase) : ViewModel() {
    fun setFavoriteNewsIndo(indoNewsTittle: IndoNews, newStatus: Boolean) =
        newsIndoUseCase.setFavoriteNewsIndo(
            indoNewsTittle,
            newStatus
        )
}