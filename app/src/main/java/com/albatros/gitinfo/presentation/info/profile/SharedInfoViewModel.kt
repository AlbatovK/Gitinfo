package com.albatros.gitinfo.presentation.info.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navArgs = ProfileInfoFragmentArgs.fromSavedStateHandle(savedStateHandle)



}