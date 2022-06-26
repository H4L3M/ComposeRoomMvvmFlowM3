package com.nokhba.composeroommvvmflowm3.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nokhba.composeroommvvmflowm3.model.User

@Dao
interface AppDao {

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers() : LiveData<List<User>>

}