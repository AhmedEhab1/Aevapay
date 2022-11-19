package com.aevapay.assignment.app.home.network

import com.aevapay.assignment.app.home.models.Repo
import com.aevapay.assignment.app.home.util.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constant.SERVICE_REPOS)
    fun getRepos(
        @Query("page") page : Int,
        @Query("per_page") perPage : Int ?= 15
    ) : Call<List<Repo>>
}