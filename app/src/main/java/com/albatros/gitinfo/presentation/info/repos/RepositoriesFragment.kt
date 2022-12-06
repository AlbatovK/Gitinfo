package com.albatros.gitinfo.presentation.info.repos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.albatros.gitinfo.R
import com.albatros.gitinfo.presentation.info.profile.SharedInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: SharedInfoViewModel by hiltNavGraphViewModels(R.id.bottom_nav)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}