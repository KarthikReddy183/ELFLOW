package com.android.elflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.elflow.data.repository.UserDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val usersRepository: UserDetailsRepository) : ViewModel() {

    private val _emailState = MutableStateFlow<String?>(null)
    val emailState = _emailState.asStateFlow()

    fun saveEmail(email: String) {
        viewModelScope.launch {
            usersRepository.saveEmail(email)
            _emailState.value = email
        }
    }

    fun loadEmail() {
        viewModelScope.launch {
            val email = usersRepository.getEmail()
            _emailState.value = email
        }
    }

}