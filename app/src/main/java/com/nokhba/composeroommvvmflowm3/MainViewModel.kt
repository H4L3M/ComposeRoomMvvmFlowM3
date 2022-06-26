package com.nokhba.composeroommvvmflowm3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nokhba.composeroommvvmflowm3.data.UserRepo
import com.nokhba.composeroommvvmflowm3.data.getDatabase
import com.nokhba.composeroommvvmflowm3.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val allUsers: LiveData<List<User>>
    private val repo: UserRepo

    init {
        val dao = getDatabase(app).userDao
        repo = UserRepo(dao)
        allUsers = dao.getUsers()
    }

    fun addUSer(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user = user)
        }
    }

    val users = repo.users
}