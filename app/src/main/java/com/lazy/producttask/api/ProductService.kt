package com.lazy.producttask.api

import com.lazy.producttask.model.Product
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL_PRODUCT: String = "https://fakestoreapi.com"

interface ProductService {

    @GET("/products")
    suspend fun getAllProducts(): Response<Product>
}