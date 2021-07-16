package com.example.roomtutorial.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtutorial.data.local.entities.User
import com.example.roomtutorial.databinding.UserItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList: List<User> = mutableListOf()

    fun updateUserList(listOfUsers:List<User>){
        this.userList = listOfUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bindTo(userList[position])


    override fun getItemCount(): Int = userList.size

    class UserViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(user: User) {
            with(binding) {
                userId.text = user.id.toString()
                userName.text = "${user.firstName} ${user.lastName}"
            }
        }
    }
}