package com.example.roomtutorial.ui.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtutorial.data.local.entities.User
import com.example.roomtutorial.databinding.UserItemBinding

class UserAdapter(val clickedItem: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList: List<User> = mutableListOf()
    var myHolder: UserViewHolder? = null

    // function to be called with an updated list of Users from the database.
    fun updateUserList(listOfUsers: List<User>) {
        this.userList = listOfUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickedItem
        ) //pass in the binding and the lambda
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        this.myHolder = holder
        holder.bindTo(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(var binding: UserItemBinding, var clickedItem: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(user: User) {
            with(binding) {
                userId.text = user.id.toString()
                userName.text = user.firstName
                lastName.text = user.lastName

                root.setOnClickListener {
                    //invoke the lambda
                    val user = User(
                        id = userId.text.toString().toInt(),
                        firstName = userName.text.toString(),
                        lastName = lastName.text.toString()
                    )
                    //Call the lambda function when the root item is clicked
                    clickedItem.invoke(user)
                }
            }
        }
    }
}


