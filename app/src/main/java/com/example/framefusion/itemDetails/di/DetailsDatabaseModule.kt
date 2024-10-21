package com.example.framefusion.itemDetails.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.itemDetails.data.local.ItemDetailsDatabase
import com.example.framefusion.itemDetails.data.local.dao.ItemDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailsDatabaseModule {

    @Provides
    fun provideItemDetailsDatabase(@ApplicationContext appContext: Context): ItemDetailsDatabase {
        return Room.databaseBuilder(appContext, ItemDetailsDatabase::class.java, "item_details")
            .build()
    }

    @Provides
    fun provideItemDetailsDao(itemDetailsDatabase: ItemDetailsDatabase): ItemDetailsDao {
        return itemDetailsDatabase.itemDetailsDao()
    }
}