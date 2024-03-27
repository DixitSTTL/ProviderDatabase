package com.app.providerdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.providerdatabase.businesslogic.viewmodel.ViewModelMain
import com.app.providerdatabase.databinding.ActivityMainBinding
import com.app.providerdatabase.view.adapter.AdapterUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: ViewModelMain
    val mAdapter: AdapterUser = AdapterUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mViewModel = ViewModelProvider(this@MainActivity).get(ViewModelMain::class.java)
        _init()
        observer()
        getContent()

    }

    private fun observer() {
        mViewModel.userList.observe(this, {
            mAdapter.setData(it)

        })

        mViewModel.observeRefreshing.observe(this, {

            mBinding.swipeRefresh.isRefreshing = it
        })


    }

    private fun getContent() {
        mViewModel.fetchUsers()
    }

    private fun _init() {
        mBinding.apply {
            recUsers.adapter = mAdapter
            recUsers.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
                override fun onRefresh() {
                    mViewModel.observeRefreshing.postValue(true)
                    mViewModel.fetchUsers()
                }

            })
        }
    }
}