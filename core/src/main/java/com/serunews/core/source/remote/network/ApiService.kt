package com.serunews.core.source.remote.network

import com.serunews.core.source.remote.response.IndoNewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("indonews")
    suspend fun getNews(): IndoNewsResponse
}