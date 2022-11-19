package com.aevapay.assignment.app.home.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aevapay.assignment.app.home.models.Repo

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo_list")
    fun getRepos(): LiveData<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repoList : List<Repo>)

    @Query("DELETE FROM repo_list")
    fun delete()

}