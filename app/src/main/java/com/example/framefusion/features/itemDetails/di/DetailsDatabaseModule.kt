package com.example.framefusion.features.itemDetails.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.features.itemDetails.data.local.ActorDetailsDatabase
import com.example.framefusion.features.itemDetails.data.local.ItemDetailsDatabase
import com.example.framefusion.features.itemDetails.data.local.dao.ActorDetailsDao
import com.example.framefusion.features.itemDetails.data.local.dao.ItemDetailsDao
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


    @Provides
    fun provideActorDetailsDatabase(@ApplicationContext appContext: Context): ActorDetailsDatabase {
        return Room.databaseBuilder(appContext, ActorDetailsDatabase::class.java, "actor_details")
            .build()
    }

    @Provides
    fun provideActorDetailsDao(actorDetailsDatabase: ActorDetailsDatabase): ActorDetailsDao {
        return actorDetailsDatabase.actorDetailsDao()
    }
}