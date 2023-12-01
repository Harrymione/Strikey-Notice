package com.example.myapplication.retrofit

import com.example.myapplication.api.FoodSticker
import retrofit2.http.GET

interface ServerAPI {
    @GET("random.php")
    fun getRandomFood():retrofit2.Call<FoodSticker>
}