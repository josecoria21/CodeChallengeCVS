package dev.propoc.codechallengecvs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.propoc.codechallengecvs.network.ApiService
import dev.propoc.codechallengecvs.repository.SearchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): SearchRepository {
        return SearchRepository(apiService)
    }
}

