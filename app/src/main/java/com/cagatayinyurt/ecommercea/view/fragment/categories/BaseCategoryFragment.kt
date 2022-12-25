package com.cagatayinyurt.ecommercea.view.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cagatayinyurt.ecommercea.R
import com.cagatayinyurt.ecommercea.adapter.BestProductAdapter
import com.cagatayinyurt.ecommercea.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment: Fragment(R.layout.fragment_base_category) {

    private lateinit var binding: FragmentBaseCategoryBinding
    private lateinit var offerAdapter: BestProductAdapter
    private lateinit var bestProductsAdapter: BestProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOfferRecyclerView()
        setupBestProductsRecyclerView()
    }

    private fun setupBestProductsRecyclerView() {
        bestProductsAdapter = BestProductAdapter()
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(
                requireContext(), 2, GridLayoutManager.VERTICAL, false
            )
            adapter = bestProductsAdapter
        }
    }

    private fun setupOfferRecyclerView() {
        offerAdapter = BestProductAdapter()
        binding.rvBestProducts.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = offerAdapter
        }
    }
}