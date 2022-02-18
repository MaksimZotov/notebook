package com.maksimzotov.notebook.di.main

import com.maksimzotov.notebook.di.data.DataModule
import com.maksimzotov.notebook.di.domain.DomainModule
import dagger.Module

@Module(includes = [
    DataModule::class,
    DomainModule::class
])
class AppModule