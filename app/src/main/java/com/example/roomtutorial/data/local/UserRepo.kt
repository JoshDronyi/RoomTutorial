package com.example.roomtutorial.data.local

import android.app.Application
import com.example.roomtutorial.data.local.entities.User


class UserRepo(application: Application) {

    private val userDao by lazy {
        UserDatabase.getDatabase(application).userDao()
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun insertUser(user: User) {
        return userDao.insertUser(user)
    }

    suspend fun deleteUsers() {
        return userDao.deleteAllUsers()
    }
}



