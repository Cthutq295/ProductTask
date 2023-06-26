package com.lazy.producttask.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazy.producttask.R
import com.lazy.producttask.adapter.ProductListAdapter
import com.lazy.producttask.databinding.FragmentProductListBinding
import com.lazy.producttask.model.ProductItem
import com.lazy.producttask.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list),
    ProductListAdapter.OnProductClickListener, MenuProvider {
    private val viewModel: ProductListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProductListBinding.bind(view)

        val productListAdapter = ProductListAdapter(this, requireContext())
        binding.rvProductList.adapter = productListAdapter
        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductList.setHasFixedSize(true)

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner) {
            productListAdapter.submitList(it)

            if (productListAdapter.itemCount >= 0) {
                binding.rvProductList.visibility = View.VISIBLE
                binding.tvListIsEmpty.visibility = View.GONE
            } else {
                binding.rvProductList.visibility = View.GONE
                binding.tvListIsEmpty.visibility = View.VISIBLE
            }
        }
    }

    override fun onProductItemClick(item: ProductItem) {
        val direction =
            ProductListFragmentDirections.actionProductListFragmentToProductInfoFragment(
                item.title,
                item
            )
        findNavController().navigate(direction)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.top_bar, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.aboutFragment -> {
                val direction =
                    ProductListFragmentDirections.actionProductListFragmentToAboutAppFragment()
                findNavController().navigate(direction)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.addMenuProvider(this)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().removeMenuProvider(this)
    }
}