package com.example.myshop_api.Repository

import com.example.myshop_api.apii.ApiClient
import com.example.myshop_api.apii.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.myshop_api.model.ProductsResponse
import retrofit2.Response

class productrepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getProducts():Response<ProductsResponse>{
        return withContext(Dispatchers.IO){
            apiClient.getProducts()
        }
    }
}