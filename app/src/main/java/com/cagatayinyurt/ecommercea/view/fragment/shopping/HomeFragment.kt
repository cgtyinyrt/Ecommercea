package com.cagatayinyurt.ecommercea.view.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cagatayinyurt.ecommercea.R
import com.cagatayinyurt.ecommercea.adapter.HomeViewPagerAdapter
import com.cagatayinyurt.ecommercea.databinding.FragmentHomeBinding
import com.cagatayinyurt.ecommercea.view.fragment.categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            FurnitureFragment(),
            AccessoriesFragment()
        )

        val viewPager2Adapter = HomeViewPagerAdapter(
            categoriesFragments,
            childFragmentManager,
            lifecycle
        )

        binding.viewPagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessories"
                5 -> tab.text = "Furniture"
            }
        }.attach()

        binding.viewPagerHome.isUserInputEnabled = false
    }
}