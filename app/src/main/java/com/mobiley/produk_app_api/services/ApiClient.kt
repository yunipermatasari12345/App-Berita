package com.mobiley.produk_app_api.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(interceptor())//funtion d bawah
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun interceptor() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()

    }
    //panggil produk service
    val produkService : ProdukService by lazy {
        retrofit.create(ProdukService::class.java)
    }

}