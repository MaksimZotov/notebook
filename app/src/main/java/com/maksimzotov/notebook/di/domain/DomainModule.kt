package com.maksimzotov.notebook.di.domain

import com.maksimzotov.notebook.di.qualifiers.DispatcherDefault
import com.maksimzotov.notebook.di.qualifiers.DispatcherIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DomainModule {
    @Provides
    @DispatcherDefault
    fun provideCoroutineDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @DispatcherIO
    fun provideCoroutineDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}