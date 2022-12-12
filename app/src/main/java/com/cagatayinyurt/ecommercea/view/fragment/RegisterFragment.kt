package com.cagatayinyurt.ecommercea.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cagatayinyurt.ecommercea.R
import com.cagatayinyurt.ecommercea.data.User
import com.cagatayinyurt.ecommercea.databinding.FragmentRegisterBinding
import com.cagatayinyurt.ecommercea.util.RegisterValidation
import com.cagatayinyurt.ecommercea.util.Resource
import com.cagatayinyurt.ecommercea.viewmodel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

private val TAG = "RegisterFragment"

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            binding.btnRegister.setOnClickListener {
                val user = User(
                    edFirstName.text.toString().trim(),
                    edLastName.text.toString().trim(),
                    edEmailLogin.text.toString().trim()
                )
                val password = edPasswordLogin.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.btnRegister.startAnimation()
                    }
                    is Resource.Success -> {
                        Log.d("test", it.data.toString())
                        binding.btnRegister.revertAnimation()
                    }
                    is Resource.Error -> {
                        Log.e(TAG, it.message.toString())
                        binding.btnRegister.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.edEmailLogin.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }
                if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.edPasswordLogin.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}