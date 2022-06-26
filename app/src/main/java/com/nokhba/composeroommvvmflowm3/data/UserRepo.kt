package com.nokhba.composeroommvvmflowm3.data

import androidx.lifecycle.LiveData
import com.nokhba.composeroommvvmflowm3.model.User

class UserRepo(private val userDao: AppDao) {

    val users: LiveData<List<User>> = userDao.getUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user = user)
    }

}