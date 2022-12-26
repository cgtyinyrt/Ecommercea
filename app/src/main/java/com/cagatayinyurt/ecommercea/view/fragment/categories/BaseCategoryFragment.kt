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
    protected val offerAdapter: BestProductAdapter by lazy { BestProductAdapter() }
    protected val bestProductsAdapter: BestProductAdapter by lazy { BestProductAdapter() }

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

//        binding.rvOffer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if (!recyclerView.canScrollHorizontally(1) && dx != 0) {
//                    //onOfferPagingRequest()
//                }
//            }
//        })

//        binding.nestedScrollBaseCategory
//            .setOnScrollChangeListener(
//                NestedScrollView.OnScrollChangeListener{ v, _, scrollY, _, _ ->
//            if (v.getChildAt(0).bottom <= v.height + scrollY) {
//                //onBestProductsPagingRequest()
//            }
//        })
    }

//    open fun onOfferPagingRequest() {
//    }
//
//    open fun onBestProductsPagingRequest() {
//    }

    private fun setupBestProductsRecyclerView() {
        //bestProductsAdapter = BestProductAdapter()
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(
                requireContext(), 2, GridLayoutManager.VERTICAL, false
            )
            adapter = bestProductsAdapter
        }
    }

    private fun setupOfferRecyclerView() {
        //offerAdapter = BestProductAdapter()
        binding.rvOffer.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = offerAdapter
        }
    }

    fun showOfferLoading() {
        binding.offerProductsProgressBar.visibility = View.VISIBLE
    }

    fun hideOfferLoading() {
        binding.offerProductsProgressBar.visibility = View.GONE
    }

    fun showBestProductsLoading() {
        binding.bestProductsProgressBar.visibility = View.VISIBLE
    }

    fun hideBestProductsLoading() {
        binding.bestProductsProgressBar.visibility = View.GONE
    }
}