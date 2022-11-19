package com.aevapay.assignment.app.home.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_list")
data class Repo(
    @PrimaryKey val id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val watchers: Int? = 0,
    val url: String? = "",
    @Embedded
    var owner: OwnerData? = null

)
