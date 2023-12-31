package com.lazy.producttask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
) : Parcelable