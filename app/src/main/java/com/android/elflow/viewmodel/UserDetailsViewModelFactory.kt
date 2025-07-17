package com.android.elflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.elflow.data.repository.UserDetailsRepository

class UserDetailsViewModelFactory(private val repository: UserDetailsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserDetailsViewModel(usersRepository = repository) as T
        }
        throw IllegalStateException("Un Known class")
    }

}