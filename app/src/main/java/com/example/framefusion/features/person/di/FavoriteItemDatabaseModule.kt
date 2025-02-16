package com.example.framefusion.features.person.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.features.person.data.local.FavoriteItemDatabase
import com.example.framefusion.features.person.data.local.dao.FavoriteItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteItemDatabaseModule {

    @Provides
    fun provideFavoriteItemDatabase(@ApplicationContext appContext: Context): FavoriteItemDatabase {
        return Room.databaseBuilder(appContext, FavoriteItemDatabase::class.java, "favorite_item")
            .build()
    }

    @Provides
    fun provideFavoriteItemMovieDao(favoriteItemDatabase: FavoriteItemDatabase): FavoriteItemDao {
        return favoriteItemDatabase.favoriteItemDao()
    }
}