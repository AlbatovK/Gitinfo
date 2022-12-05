package com.albatros.gitinfo.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albatros.gitinfo.common.Resource
import com.albatros.gitinfo.domain.model.GithubUser
import com.albatros.gitinfo.domain.use_case.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<Resource<GithubUser>>()
    val userInfo: LiveData<Resource<GithubUser>> = _userInfo

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()

    sealed class LoginEvents {
        object InvalidLogin : LoginEvents()
    }

    fun onLoginEntered(login: String) = viewModelScope.launch(Dispatchers.Main) {

        if (login.isBlank() || login.length > 20) {
            _events.send(LoginEvents.InvalidLogin)
            return@launch
        }

        _userInfo.value = Resource.Loading()

        _userInfo.value = try {
            val repoDetails = getUserInfoUseCase(login)
            Resource.Success(repoDetails)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage)
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage)
        }
    }
}