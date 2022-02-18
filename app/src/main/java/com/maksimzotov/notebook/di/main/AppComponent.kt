package com.maksimzotov.notebook.di.main

import android.content.Context
import com.maksimzotov.notebook.presenter.view.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: AboutFragment)

    fun inject(fragment: ItemAboutDetailsFragment)

    fun inject(fragment: NoteDetailsFragment)

    fun inject(fragment: NotesListFragment)

    fun inject(fragment: SettingsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}