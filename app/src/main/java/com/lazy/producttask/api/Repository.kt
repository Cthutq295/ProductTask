package com.lazy.producttask.api

import com.lazy.producttask.model.Product
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val productService: ProductService) {
    suspend fun getAllProducts() : Response<Product> {
        return productService.getAllProducts()
    }
}