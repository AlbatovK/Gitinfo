package com.albatros.gitinfo.presentation.info.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.albatros.gitinfo.R
import com.albatros.gitinfo.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileInfoFragment : Fragment(R.layout.fragment_profile) {

    private val args by navArgs<ProfileInfoFragmentArgs>()
    private val viewModel: SharedInfoViewModel by hiltNavGraphViewModels(R.id.bottom_nav)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userInfo.collect {
                    binding.info.text = it.toString()
                }
            }
        }
    }
}