package com.lazy.producttask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazy.producttask.R
import com.lazy.producttask.databinding.ItemProductBinding
import com.lazy.producttask.model.ProductItem

class ProductListAdapter(
    private val listener: OnProductClickListener,
    private val context: Context
) : ListAdapter<ProductItem, ProductListAdapter.ProductViewHolder>(DiffCallback()) {
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductItem) {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    if (product != null) {
                        listener.onProductClick(product)
                    }
                }
            }

            binding.apply {
                tvProductId.text = "Номер товара: " + product.id.toString()
                tvProductName.text = product.title
                val priceInRub = (product.price.toFloat() * 70).toString()
                tvProductPrice.text = "Цена: " + priceInRub + " Руб."

                Glide.with(context)
                    .load(product.image)
                    .error(R.drawable.error_image)
                    .into(ivProductIcon)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }

    }

    interface OnProductClickListener {
        fun onProductClick(item: ProductItem)
    }
}