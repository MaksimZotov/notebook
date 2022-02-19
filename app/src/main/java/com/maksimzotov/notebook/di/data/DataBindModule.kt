package com.maksimzotov.notebook.di.data

import com.maksimzotov.notebook.data.RepositoryImpl
import com.maksimzotov.notebook.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataBindModule {

    @Binds
    @Singleton
    fun bindRepositoryImplToRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}