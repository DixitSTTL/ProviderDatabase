package com.app.providerdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        mViewModel.userList.observe(this,{
            mAdapter.setData(it)

        })


    }

    private fun getContent() {
        mViewModel.fetchUsers()
    }

    private fun _init() {
       mBinding.recUsers.adapter= mAdapter
       mBinding.recUsers.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }
}