package com.lazy.producttask.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lazy.producttask.R
import com.lazy.producttask.databinding.FragmentProductInfoBinding
import com.lazy.producttask.model.ProductItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductInfoFragment : Fragment(R.layout.fragment_product_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductInfoBinding.bind(view)
        val args = arguments
        val product: ProductItem = args!!.getParcelable("product")!!

        binding.tvProductName.text = product.title
        binding.tvProductPrice.text = "Price: " + product.price + " $"
        binding.tvProductDescription.text = product.description

        Glide.with(requireContext())
            .load(product.image)
            .error(R.drawable.error_image)
            .into(binding.ivProductImage)
    }
}