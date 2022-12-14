package com.aevapay.assignment

import androidx.multidex.MultiDexApplication
import androidx.room.Room
import com.aevapay.assignment.app.home.database.AppDatabase

class MyApp : MultiDexApplication() {

    companion object {
        lateinit var appDataBase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        provideDatabase()
    } // fun of onCreate

    private fun provideDatabase() {
       appDataBase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "aevapay")
           .allowMainThreadQueries()
           .fallbackToDestructiveMigration()
           .build()
    }  // fun of provideDatabase
}