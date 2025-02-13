package com.example.framefusion.features.search.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.features.search.data.ItemSearchDatabase
import com.example.framefusion.features.search.data.Top10hdDatabase
import com.example.framefusion.features.search.data.local.dao.SearchItemDao
import com.example.framefusion.features.search.data.local.dao.Top10hdDao
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

    @Provides
    fun provideTop10hdDatabase(@ApplicationContext appContext: Context): Top10hdDatabase {
        return Room.databaseBuilder(appContext, Top10hdDatabase::class.java, "top10hd").build()
    }

    @Provides
    fun provideTop10hdMovieDao(top10hdDatabase: Top10hdDatabase): Top10hdDao {
        return top10hdDatabase.top10hdDao()
    }
}