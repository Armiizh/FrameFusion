package com.example.framefusion.search.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.search.data.ItemSearchDatabase
import com.example.framefusion.search.data.local.dao.SearchItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchItemDatabaseModule {

    @Provides
    fun provideSearchItemDatabase(@ApplicationContext appContext: Context): ItemSearchDatabase {
        return Room.databaseBuilder(appContext, ItemSearchDatabase::class.java, "search_item").build()
    }

    @Provides
    fun provideSearchItemMovieDao(itemSearchDatabase: ItemSearchDatabase): SearchItemDao {
        return itemSearchDatabase.searchItemDao()
    }
}