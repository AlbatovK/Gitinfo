package com.albatros.gitinfo.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.albatros.gitinfo.R
import com.albatros.gitinfo.databinding.FragmentHostBinding
import com.albatros.gitinfo.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavHostFragment : Fragment() {

    private lateinit var binding: FragmentHostBinding
    private val args by navArgs<BottomNavHostFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHostBinding.inflate(inflater, container, false)

        val navHostFragment: View = binding.container.findViewById(R.id.bottom_nav_host_fragment)
        val navController = Navigation.findNavController(navHostFragment)
        navController.setGraph(
            R.navigation.bottom_nav_graph, args.toBundle()
        )

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.profile_info_fragment,
            R.id.repositories_fragment
        ).build()

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(
            activity as MainActivity,
            navController,
            appBarConfiguration
        )

        return binding.root
    }
}