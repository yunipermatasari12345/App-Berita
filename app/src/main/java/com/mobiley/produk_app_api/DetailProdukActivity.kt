package com.mobiley.produk_app_api

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailProdukActivity : AppCompatActivity() {
    private lateinit var imgThumbnail : ImageView
    private  lateinit var tvTitle : TextView
    private  lateinit var tvCategory : TextView
    private  lateinit var tvDescription : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_produk)

        imgThumbnail = findViewById(R.id.imgThumbnail)
        tvTitle = findViewById(R.id.tvTitle)
        tvCategory = findViewById(R.id.tvCategory)
        tvDescription = findViewById(R.id.tvDescription)

        val gambar = intent.getStringExtra("gambar")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("descrepsion")

        Glide.with(this).load(gambar).centerCrop()
            .into(imgThumbnail)

        tvTitle.text = title

    }
}