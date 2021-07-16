package com.example.roomtutorial.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtutorial.data.local.entities.User
import com.example.roomtutorial.databinding.ActivityMainBinding
import com.example.roomtutorial.ui.adapters.UserAdapter
import com.example.roomtutorial.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(MainViewModel::class.java)

        userAdapter = UserAdapter() { user ->
            //Remove the user
            mainViewModel.removeUser(user)
            //get the new list of Users
            mainViewModel.getAllUsers()
        } //Lambda passed into the click listener in the UserViewHolder

        setUpObservables()

        setUpBinding()
    }
    private fun setUpBinding(){
        with(binding) {
            rvUsers.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            rvUsers.adapter = userAdapter

            add.setOnClickListener {
                val user =
                    User(firstName = firstName.text.toString(), lastName = lastName.text.toString())
                mainViewModel.insertUser(user)
            }
        }
    }

    private fun setUpObservables() {
        mainViewModel.userList.observe(this, {
            userAdapter.updateUserList(it)
            mainViewModel.getAllUsers()
        })
    }
}