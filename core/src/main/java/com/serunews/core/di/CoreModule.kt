package com.serunews.core.di

import androidx.room.Room
import com.serunews.core.BuildConfig
import com.serunews.core.domain.repository.INewsIndoRepository
import com.serunews.core.source.NewsIndoRepository
import com.serunews.core.source.local.LocalDataSource
import com.serunews.core.source.local.room.IndoNewsDatabase
import com.serunews.core.source.remote.RemoteDataSource
import com.serunews.core.source.remote.network.ApiService
import com.serunews.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<IndoNewsDatabase>().indoNewsDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("serunewsapp123".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            IndoNewsDatabase::class.java, "SerunewsDB.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsIndoRepository> { NewsIndoRepository(get(), get(), get()) }
}