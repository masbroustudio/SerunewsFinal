package com.serunews.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.serunews.core.domain.usecase.NewsIndoUseCase

class FavoriteViewModel(newsIndoUseCase: NewsIndoUseCase) : ViewModel() {
    val favoriteNewsTech = newsIndoUseCase.getFavoriteNewsTech().asLiveData()
}