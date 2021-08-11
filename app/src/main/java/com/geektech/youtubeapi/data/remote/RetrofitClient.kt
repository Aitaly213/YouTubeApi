package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideApi(get()) }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .connectTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(30,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)