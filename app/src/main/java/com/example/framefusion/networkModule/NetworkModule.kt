package com.example.framefusion.networkModule

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.framefusion.features.home.data.service.HomeService
import com.example.framefusion.features.itemDetails.data.service.ItemDetailsService
import com.example.framefusion.features.search.data.service.SearchService
import com.example.framefusion.utils.interceptors.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.kinopoisk.dev/v1.4/"

    @Provides
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    @Provides
    fun provideChuckerInterceptor(chuckerCollector: ChuckerCollector, @ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("X-API-KEY")
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Provides
    fun provideItemDetailsService(retrofit: Retrofit): ItemDetailsService {
        return retrofit.create(ItemDetailsService::class.java)
    }
}