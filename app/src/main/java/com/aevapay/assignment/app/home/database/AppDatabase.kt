package com.aevapay.assignment.app.home.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aevapay.assignment.app.home.models.Repo

@Database(entities = [Repo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}