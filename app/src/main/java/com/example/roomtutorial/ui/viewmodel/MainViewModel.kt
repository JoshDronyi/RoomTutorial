package com.example.roomtutorial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomtutorial.data.local.UserRepo
import com.example.roomtutorial.data.local.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo: UserRepo by lazy {
        UserRepo(application)

    }
    private var _userList: MutableLiveData<List<User>> = MutableLiveData()
    val userList: LiveData<List<User>> get() = _userList

    fun getAllUsers() = viewModelScope.launch(Dispatchers.IO) {
        val users = userRepo.getAllUsers()
        _userList.postValue(users)
    }

    fun insertUser(user:User)= viewModelScope.launch(Dispatchers.IO) {
       userRepo.insertUser(user)
        getAllUsers()
    }

    fun removeUser(user:User) = viewModelScope.launch(Dispatchers.IO) {
        userRepo.deleteSpecificUser(user)
    }



}