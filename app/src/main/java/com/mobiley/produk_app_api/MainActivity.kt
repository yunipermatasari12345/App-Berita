package com.mobiley.produk_app_api

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mobiley.produk_app_api.adapter.ProdukAdapter
import com.mobiley.produk_app_api.model.ModelProduk
import com.mobiley.produk_app_api.model.ResponseProduk
import com.mobiley.produk_app_api.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call : Call<ResponseProduk>
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_produk)

        produkAdapter = ProdukAdapter { modelProduk : ModelProduk -> produkOnClick(modelProduk) }
        recyclerView.adapter = produkAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager.VERTICAL,false
        )
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()

    }



    private fun produkOnClick(modelProduk: ModelProduk) {
        Toast.makeText(applicationContext, modelProduk.description,
            Toast.LENGTH_SHORT).show()

        val  intent = Intent(this,DetailProdukActivity::class.java)
        intent.putExtra("gambar",modelProduk.thumbnail)
        intent.putExtra("title",modelProduk.title)
        startActivity(intent)
    }
    private fun getData() {
        swipeRefreshLayout.isRefreshing = true
        call = ApiClient.produkService.getAllProduk()
        call.enqueue(object : Callback<ResponseProduk> {
            override fun onResponse(
                call: Call<ResponseProduk>,
                response: Response<ResponseProduk>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful){
                    produkAdapter.submitList(response.body()?.products)
                    produkAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseProduk>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext,
                    t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })
    }
}