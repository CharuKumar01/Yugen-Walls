package com.example.yugenwalls.Retrofit

import com.example.yugenwalls.Data.RandomAnimeImage
import retrofit2.Response
import retrofit2.http.GET

interface NekosAPI {
    @GET("random?rating=safe&limit=30")
    suspend fun getRandomAnime() : Response<List<RandomAnimeImage>>
}