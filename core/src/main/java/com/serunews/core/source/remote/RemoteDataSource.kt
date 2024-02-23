package com.serunews.core.source.remote

import android.util.Log
import com.serunews.core.source.remote.network.ApiResponse
import com.serunews.core.source.remote.network.ApiService
import com.serunews.core.source.remote.response.PostsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllNewsTech(): Flow<ApiResponse<List<PostsItem>>> {
        return flow {
            try {
                val response = apiService.getNews()
                val dataArray = response.posts

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.posts))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(dispatcher)
    }
}