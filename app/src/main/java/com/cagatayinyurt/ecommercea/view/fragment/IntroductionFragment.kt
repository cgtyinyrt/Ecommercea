package com.cagatayinyurt.ecommercea.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cagatayinyurt.ecommercea.R
import com.cagatayinyurt.ecommercea.databinding.FragmentIntroductionBinding
import com.cagatayinyurt.ecommercea.view.activity.ShoppingActivity
import com.cagatayinyurt.ecommercea.viewmodel.IntroductionViewModel
import com.cagatayinyurt.ecommercea.viewmodel.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENT
import com.cagatayinyurt.ecommercea.viewmodel.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroductionFragment : Fragment(R.layout.fragment_introduction) {

    private lateinit var binding: FragmentIntroductionBinding
    private val viewModel by viewModels<IntroductionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.navigate.collect {
                when(it) {
                    SHOPPING_ACTIVITY -> {
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            )
                            startActivity(intent)
                        }
                    }
                    ACCOUNT_OPTIONS_FRAGMENT -> {
                        findNavController().navigate(it)
                    }
                    else -> Unit
                }
            }
        }

        binding.btnStart.setOnClickListener {
            viewModel.startButtonClick()
            findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)
        }
    }
}