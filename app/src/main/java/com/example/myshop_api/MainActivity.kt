package com.example.myshop_api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop_api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val  layoutManager:RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        

    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }

    fun getProducts() {
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getProducts()
        request.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {

                    var products = response.body()?.products

                    var productAdapter=ProductsAdapter(products?: emptyList())
                    binding.rvproducts.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvproducts.adapter = productAdapter

                    Toast.makeText(
                        baseContext,
                        "fetched ${products?.size}products",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }

            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })


    }
}