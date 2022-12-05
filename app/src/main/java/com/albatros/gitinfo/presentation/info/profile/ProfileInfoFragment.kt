package com.albatros.gitinfo.presentation.info.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.albatros.gitinfo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileInfoFragment : Fragment(R.layout.fragment_second) {

    private val args by navArgs<ProfileInfoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}