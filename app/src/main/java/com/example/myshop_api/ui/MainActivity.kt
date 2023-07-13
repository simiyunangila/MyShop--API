package com.example.myshop_api.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop_api.R
import com.example.myshop_api.ViewModel.productsViewModel
import com.example.myshop_api.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val productsViewModel: productsViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)


    }

    override fun onResume() {
        super.onResume()
        productsViewModel.fetchProducts()
        productsViewModel.producctLiveData.observe(this, Observer { productList ->

            var productAdapter = ProductsAdapter(productList ?: emptyList())
            binding.rvproducts.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvproducts.adapter = productAdapter

            Toast.makeText(
                baseContext, "fetched ${productList?.size}products", Toast.LENGTH_LONG
            ).show()

        })
        productsViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }
}

