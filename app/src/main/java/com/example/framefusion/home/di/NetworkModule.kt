package com.example.framefusion.home.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.framefusion.home.data.rest.KinopoiskApi
import com.example.framefusion.utils.Constants.BASE_URL
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
    fun provideApiService(retrofit: Retrofit): KinopoiskApi {
        return retrofit.create(KinopoiskApi::class.java)
    }
}
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor())
//                .build())
//            .build()
//    }
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): KinopoiskApi {
//        return retrofit.create(KinopoiskApi::class.java)
//    }
//}

//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url("https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10&selectFields=id&selectFields=name&selectFields=poster&selectFields=backdrop&selectFields=year&selectFields=genres&selectFields=movieLength&selectFields=rating&selectFields=shortDescription&selectFields=persons&notNullFields=top250&sortField=top250&sortType=1&type=movie&genres.name=%D0%BA%D0%BE%D0%BC%D0%B5%D0%B4%D0%B8%D1%8F&lists=top250")
//        .get()
//        .addHeader("accept", "application/json")
//        .addHeader("X-API-KEY", Constants.X_API_KEY)
//        .build()
//
//    val response = client.newCall(request).execute()

//private val chuckerCollector = ChuckerCollector(
//    context = application,
//    showNotification = true,
//    retentionPeriod = RetentionManager.Period.ONE_HOUR
//)
//private val chuckerInterceptor = ChuckerInterceptor.Builder(application).collector(chuckerCollector)
//    .maxContentLength(250_000L).redactHeaders("Auth-Token", "Bearer")
//    .alwaysReadResponseBody(true).createShortcut(true).build()
//
////Api With Header
//private val interceptorWithToken = HttpLoggingInterceptor().apply {
//    level = HttpLoggingInterceptor.Level.BODY
//}
//private var clientWithToken = OkHttpClient.Builder().addNetworkInterceptor(interceptorWithToken)
//    .addInterceptor(AuthorizationInterceptor(this)).addInterceptor(chuckerInterceptor).build()
//private var retrofitWithToken =
//    Retrofit.Builder().baseUrl("https://plannerok.ru/api/v1/users/").client(clientWithToken)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//private var apiWithToken = retrofitWithToken.create(Api::class.java)