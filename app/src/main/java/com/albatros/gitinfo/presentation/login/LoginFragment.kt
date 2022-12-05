package com.albatros.gitinfo.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.albatros.gitinfo.R
import com.albatros.gitinfo.common.Resource
import com.albatros.gitinfo.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            viewModel.onLoginEntered(login)
        }

        viewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val dir = LoginFragmentDirections
                        .actionLoginFragmentToBottomNavHostFragment(it.data)
                    findNavController().navigate(dir)
                }
                is Resource.Loading -> {
                    binding.loginEditText.clearFocus()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect {
                    when (it) {
                        is LoginViewModel.LoginEvents.InvalidLogin -> {
                            Toast.makeText(context, "Invalid login", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}