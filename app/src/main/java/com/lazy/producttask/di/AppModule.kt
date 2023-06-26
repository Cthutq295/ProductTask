package com.lazy.producttask.di

import android.content.Context
import android.net.ConnectivityManager
import com.lazy.producttask.api.BASE_URL_PRODUCT
import com.lazy.producttask.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNewsRetrofit(): ProductService = Retrofit.Builder()
        .baseUrl(BASE_URL_PRODUCT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductService::class.java)
}