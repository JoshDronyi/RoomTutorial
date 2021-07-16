package com.example.roomtutorial.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomtutorial.data.local.dao.UserDao
import com.example.roomtutorial.data.local.entities.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    /*
         This class represents your database as a whole
    */


    companion object {
        /*

            Static variables and method used for creation of the database.
            Application instance needed for the database builder, passed in when
            the viewModel is created in Main Activity.

         */
        private const val DB_NAME = "user.db"
        private lateinit var application: Application
        private val database: UserDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(application, UserDatabase::class.java, DB_NAME).build()
        }


        fun getDatabase(application: Application): UserDatabase {
            this.application = application
            return database
        }
    }

    // access to the userDao
    abstract fun userDao(): UserDao
}