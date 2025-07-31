package com.android.elflow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.elflow.data.repository.UserDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val usersRepository: UserDetailsRepository) : ViewModel() {

    private val _emailState = MutableStateFlow<String?>(null)
    val emailState = _emailState.asStateFlow()

    private val _data = MutableLiveData<String>() // internal modification

    val data: LiveData<String> get() = _data // Exposed as live data to prevent external modification


    private val _pwdValue = MutableLiveData<String>()

    val pwd: LiveData<String> = _pwdValue

    fun saveEmail(email: String) {
        viewModelScope.launch {
            usersRepository.saveEmail(email)
            _emailState.value = email
        }
        _data.value = email
    }

    fun loadEmail() {
        viewModelScope.launch {
            val email = usersRepository.getEmail()
            _emailState.value = email
        }
    }
}