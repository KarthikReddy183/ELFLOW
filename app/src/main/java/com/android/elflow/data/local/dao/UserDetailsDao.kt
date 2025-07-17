package com.android.elflow.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.elflow.data.local.entity.UserDetailsEntity

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmail(emailEntity: UserDetailsEntity)

    @Query("SELECT * FROM user_details WHERE id = 0")
    suspend fun getEmail(): UserDetailsEntity?

}