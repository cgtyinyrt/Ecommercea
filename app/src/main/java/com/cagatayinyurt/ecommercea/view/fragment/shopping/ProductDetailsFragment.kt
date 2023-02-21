package com.cagatayinyurt.ecommercea.view.fragment.shopping

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cagatayinyurt.ecommercea.adapter.ColorsAdapter
import com.cagatayinyurt.ecommercea.adapter.SizesAdapter
import com.cagatayinyurt.ecommercea.adapter.ViewPager2ImagesAdapter
import com.cagatayinyurt.ecommercea.data.CartProduct
import com.cagatayinyurt.ecommercea.databinding.FragmentProductDetailsBinding
import com.cagatayinyurt.ecommercea.util.Resource
import com.cagatayinyurt.ecommercea.util.hideBottomNav
import com.cagatayinyurt.ecommercea.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2ImagesAdapter() }
    private val sizesAdapter by lazy { SizesAdapter() }
    private val colorsAdapter by lazy { ColorsAdapter() }
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private var selectedColor: Int? = null
    private var selectedSize: String? = null
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNav()
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setupSizesRv()
        setupColorsRv()
        setupViewPager2Rv()

        sizesAdapter.onItemClick = {
            selectedSize = it
        }

        colorsAdapter.onItemClick = {
            selectedColor = it
        }

        binding.btnAddToCart.setOnClickListener {
            viewModel.addUpdateProductInCart(CartProduct(product, 1, selectedColor, selectedSize))
        }

        lifecycleScope.launchWhenStarted {
            viewModel.addToCart.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.btnAddToCart.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.btnAddToCart.revertAnimation()
                        Toast.makeText(requireContext(), "Product added successfully", Toast.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        binding.btnAddToCart.stopAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = "$ ${product.price}"
            tvProductDescription.text = product.description

            if (product.colors.isNullOrEmpty()) {
                tvProductColors.visibility = View.INVISIBLE
            }
            if (product.size.isNullOrEmpty()) {
                tvProductSizes.visibility = View.INVISIBLE
            }
        }

        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let { colorsAdapter.differ.submitList(it) }
        product.size?.let { sizesAdapter.differ.submitList(it) }
    }

    private fun setupSizesRv() {
        binding.rvSizes.apply {
            adapter = sizesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupColorsRv() {
        binding.rvColors.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupViewPager2Rv() {
        binding.apply {
            viewPagerProductImages.adapter = viewPagerAdapter
        }
    }
}