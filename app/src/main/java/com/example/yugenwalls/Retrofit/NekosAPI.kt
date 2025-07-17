package com.example.yugenwalls.Retrofit

import com.example.yugenwalls.Data.RandomAnimeImage
import retrofit2.Response
import retrofit2.http.GET

interface NekosAPI {
    @GET("random")
    suspend fun getRandomAnime() : Response<List<RandomAnimeImage>>
}