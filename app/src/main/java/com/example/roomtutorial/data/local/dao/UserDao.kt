package com.example.roomtutorial.data.local.dao

import androidx.room.*
import com.example.roomtutorial.data.local.entities.User

@Dao
interface UserDao {

    //Get all
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    //Delete
    @Query("DELETE  FROM user_table")
    suspend fun deleteAllUsers(): Unit

    //GetByName
    @Query("SELECT * FROM user_table WHERE user_table.first_name = :first AND user_table.last_name = :last")
    suspend fun getUserByName(first:String, last:String): User

    //Insert user
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUser(user: User)

}