package com.android.elflow

import android.app.Application
import com.android.elflow.data.local.db.UserDetailsDataBase
import com.android.elflow.data.repository.UserDetailsRepository

class ElFlowApplication : Application() {

    private val database: UserDetailsDataBase by lazy { UserDetailsDataBase.getInstance(this) }

    val repository: UserDetailsRepository by lazy { UserDetailsRepository(database.usersDao()) }

}