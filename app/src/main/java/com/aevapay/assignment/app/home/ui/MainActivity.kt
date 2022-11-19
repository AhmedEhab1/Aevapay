package com.aevapay.assignment.app.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.aevapay.assignment.R
import com.aevapay.assignment.app.home.adapters.RepoAdapter
import com.aevapay.assignment.app.home.models.Repo
import com.aevapay.assignment.app.home.network.NetworkState
import com.aevapay.assignment.app.home.util.ProgressLoading
import com.aevapay.assignment.app.home.viewModels.RepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: RepoViewModel by viewModels()
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initViewModel()
        shimmerAnimation(true)

    } // fun of onCreate

    private fun initList() {
        adapter = RepoAdapter(context = this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    } // fun of initList

    private fun initViewModel() {
        viewModel.getRepos()
        viewModel.networkState.observe(this, networkStateObserver)
        viewModel.pagingNetworkState?.observe(this, pagingNetworkStateObserver)
        viewModel.itemPagedReviewList?.observe(this, reposObserver)
    } // fun of initViewModel


    private val networkStateObserver = Observer<NetworkState> { networkState ->
        when (networkState.status) {
            NetworkState.Status.RUNNING -> {
                ProgressLoading.show(this)
            } // LOADING
            NetworkState.Status.SUCCESS -> {
                ProgressLoading.dismiss()
            }// LOADED
            NetworkState.Status.FAILED -> {
                ProgressLoading.dismiss()
                Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
            } // FAILED
        }
    } // networkStateObserver

    private val pagingNetworkStateObserver = Observer<NetworkState> { networkState ->
        adapter.setNetworkState(newNetworkState = networkState.status)
    } // networkStateObserver

    private val reposObserver = Observer<PagedList<Repo>>
    {
        adapter.submitList(it)

    } // reposObserver

    // start shimmer animation and control
    private fun shimmerAnimation(state: Boolean) {
        if (state) {
            sflMockContent.startShimmerAnimation()
            sflMockContent.visibility = View.VISIBLE
        } else sflMockContent.visibility = View.GONE
    }
}