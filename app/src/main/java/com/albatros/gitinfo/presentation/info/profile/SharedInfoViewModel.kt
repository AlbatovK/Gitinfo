package com.albatros.gitinfo.presentation.info.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albatros.gitinfo.domain.model.GithubRepoDetails
import com.albatros.gitinfo.domain.use_case.GetRepoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedInfoViewModel @Inject constructor(
    getRepoDetailsUseCase: GetRepoDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navArgs = ProfileInfoFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _userInfo = MutableStateFlow(navArgs.arg)
    val userInfo = _userInfo.asStateFlow()

    private val _repositoriesUiEvents = Channel<RepositoriesFragmentEvents>()
    val repositoriesFragmentEvents = _repositoriesUiEvents.receiveAsFlow()

    val repoDetails = _userInfo.map {
        getRepoDetailsUseCase(navArgs.arg.login)
    }.asLiveData()

    sealed class RepositoriesFragmentEvents {
        class NavigateToRepoDetailsScreen(val repo: GithubRepoDetails) : RepositoriesFragmentEvents()
    }

    fun onRepoItemClicked(repo: GithubRepoDetails) = viewModelScope.launch {
        _repositoriesUiEvents.send(RepositoriesFragmentEvents.NavigateToRepoDetailsScreen(repo))
    }
}