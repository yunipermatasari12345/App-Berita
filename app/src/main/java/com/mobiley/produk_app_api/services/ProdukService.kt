package com.mobiley.produk_app_api.services

import retrofit2.Call
import retrofit2.http.GET
import com.mobiley.produk_app_api.model.ResponseProduk

interface ProdukService {
    @GET("products") // Endpoint untuk mendapatkan semua produk
    fun getAllProduk(): Call<ResponseProduk>
}
