package com.maksimzotov.notebook.di.data

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.maksimzotov.notebook.data.RepositoryImpl
import com.maksimzotov.notebook.data.local.SettingsStorage
import com.maksimzotov.notebook.data.local.notes.NotesConstants
import com.maksimzotov.notebook.data.local.notes.NotesDao
import com.maksimzotov.notebook.data.local.notes.NotesDatabase
import com.maksimzotov.notebook.data.remote.about.AboutApi
import com.maksimzotov.notebook.data.remote.about.AboutConstants
import com.maksimzotov.notebook.data.remote.about.deserializers.ItemsAboutDeserializer
import com.maksimzotov.notebook.data.remote.about.deserializers.ItemsAboutWrapper
import com.maksimzotov.notebook.domain.Repository
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(
        settingsStorage: SettingsStorage,
        notesDao: NotesDao,
        aboutApi: AboutApi
    ): Repository {
        return RepositoryImpl(settingsStorage, notesDao, aboutApi)
    }

    @Provides
    @Singleton
    fun provideNotesDao(
        notesDatabase: NotesDatabase
    ): NotesDao {
        return notesDatabase.mainDao()
    }

    @Provides
    @Singleton
    fun provideNotesDatabase(context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            NotesConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(
        retrofit: Retrofit
    ): AboutApi {
        return retrofit.create(AboutApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        itemsAboutDeserializer: ItemsAboutDeserializer
    ): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            ItemsAboutWrapper::class.java,
            itemsAboutDeserializer
        )
        val myGson = gsonBuilder.create()
        val converterFactory = GsonConverterFactory.create(myGson)

        return Retrofit.Builder()
            .baseUrl(AboutConstants.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}