package com.serunews.newsapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.serunews.core.domain.usecase.NewsIndoUseCase

class HomeViewModel(newsIndoUseCase: NewsIndoUseCase) : ViewModel() {
    val newsIndo = newsIndoUseCase.getAllNewsIndo().asLiveData()
}