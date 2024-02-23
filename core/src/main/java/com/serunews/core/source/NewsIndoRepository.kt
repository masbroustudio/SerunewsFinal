package com.serunews.core.source

import com.serunews.core.domain.model.IndoNews
import com.serunews.core.domain.repository.INewsIndoRepository
import com.serunews.core.source.local.LocalDataSource
import com.serunews.core.source.remote.RemoteDataSource
import com.serunews.core.source.remote.network.ApiResponse
import com.serunews.core.source.remote.response.PostsItem
import com.serunews.core.utils.AppExecutors
import com.serunews.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsIndoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsIndoRepository {

    override fun getAllNewsTech(): Flow<Resource<List<IndoNews>>> =
        object : NetworkBoundResource<List<IndoNews>, List<PostsItem>>(appExecutors) {

            override fun loadFromDB(): Flow<List<IndoNews>> {
                return localDataSource.getAllNewsTech().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<IndoNews>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<PostsItem>>> {
                return remoteDataSource.getAllNewsTech()
            }

            override suspend fun saveCallResult(data: List<PostsItem>) {
                val newsTechList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNewsTech(newsTechList)
            }
        }.asFlow()

    override fun getFavoriteNewsTech(): Flow<List<IndoNews>> {
        return localDataSource.getFavoriteNewsTech().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNewsTech(indoNews: IndoNews, state: Boolean) {
        val newsTechEntity = DataMapper.mapDomainToEntity(indoNews)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNewsTech(newsTechEntity, state) }
    }

}