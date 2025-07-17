package com.example.yugenwalls.Retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {
    private const val NEKOS_BASE_URL = "https://api.nekosapi.com/v4/images/"

    @Provides
    @Singleton
    fun getNekosInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NEKOS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNekosAPI(retrofit: Retrofit): NekosAPI = retrofit.create(NekosAPI::class.java)
}