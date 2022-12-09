package com.albatros.gitinfo.presentation.info.repos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.albatros.gitinfo.R
import com.albatros.gitinfo.databinding.FragmentReposBinding
import com.albatros.gitinfo.domain.model.GithubRepoDetails
import com.albatros.gitinfo.presentation.info.BottomNavHostFragmentDirections
import com.albatros.gitinfo.presentation.info.profile.SharedInfoViewModel
import com.albatros.gitinfo.presentation.info.profile.SharedInfoViewModel.RepositoriesFragmentEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoriesFragment : Fragment(R.layout.fragment_repos), RepoAdapter.OnItemClickListener {

    private val viewModel: SharedInfoViewModel by hiltNavGraphViewModels(R.id.bottom_nav)

    override fun onItemClick(repo: GithubRepoDetails) {
        viewModel.onRepoItemClicked(repo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReposBinding.bind(view)
        val reposAdapter = RepoAdapter(this)

        with(binding) {
            with(reposRecycler) {
                setHasFixedSize(true)
                adapter = reposAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repositoriesFragmentEvents.collect {
                    when (it) {
                        is RepositoriesFragmentEvents.NavigateToRepoDetailsScreen -> {
                            val direction = BottomNavHostFragmentDirections
                                .actionBottomNavHostFragmentToRepoDetailsFragment(it.repo)

                            Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment_content_main
                            ).navigate(direction)
                        }
                    }
                }
            }
        }

        viewModel.repoDetails.observe(viewLifecycleOwner) {
            reposAdapter.submitList(it)
        }
    }
}