package com.android.elflow.data.repository

import com.android.elflow.data.local.dao.UserDetailsDao
import com.android.elflow.data.local.entity.UserDetailsEntity

class UserDetailsRepository(private val usersDao: UserDetailsDao) {

    suspend fun saveEmail(email: String) {
        usersDao.saveEmail(UserDetailsEntity(email = email))
    }

    suspend fun getEmail(): String? {
        return usersDao.getEmail()?.email
    }

}