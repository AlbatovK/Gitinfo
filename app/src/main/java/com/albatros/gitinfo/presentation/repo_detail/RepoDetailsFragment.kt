package com.albatros.gitinfo.presentation.repo_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.albatros.gitinfo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailsFragment : Fragment(R.layout.fragment_login) {

    private val args by navArgs<RepoDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, args.toString(), Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack()
        }
    }
}