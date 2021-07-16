package com.example.roomtutorial.data.local

import android.app.Application
import com.example.roomtutorial.data.local.entities.User


class UserRepo(application: Application) {

    //Instance of the DAO so we can manipulate the data table
    private val userDao by lazy {
        UserDatabase.getDatabase(application).userDao()
    }

    //Gets the users
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    //Inserts a new user
    suspend fun insertUser(user: User) {
        return userDao.insertUser(user)
    }

    //Delete all users
    suspend fun deleteUsers() {
        return userDao.deleteAllUsers()
    }

    //Delete a specific user
    suspend fun deleteSpecificUser(user: User){
        return userDao.deleteUser(user)
    }
}



