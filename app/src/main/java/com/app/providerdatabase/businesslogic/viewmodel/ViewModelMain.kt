package com.app.providerdatabase.businesslogic.viewmodel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.app.providerdatabase.pojo.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ViewModelMain @Inject constructor() : BaseViewModel() {

    var userList = MutableLiveData<ArrayList<UserModel>>()
    var observeRefreshing = MutableLiveData<Boolean>()

    @SuppressLint("Range")
    fun fetchUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            val list = ArrayList<UserModel>()
            val contentResolver = mApplication!!.contentResolver
            val authority = "com.app.providerdatabase.provider"
            val uri = Uri.parse("content://${authority}/users")
            val cursor = contentResolver.query(
                uri, null, null, null, null
            )

            cursor?.let {
                if (it.count > 0) {
                    val userIdColumn = cursor.getColumnIndex("id")
                    val userNameColumn = cursor.getColumnIndex("name")
                    val userDepColumn = cursor.getColumnIndex("department")

                    while (it.moveToNext()) {

                        val id = cursor.getInt(userIdColumn)
                        val name = cursor.getString(userNameColumn)
                        val department = cursor.getString(userDepColumn)


                        list.add(
                            UserModel(id, name, department)
                        )

                    }
                    withContext(Dispatchers.Main) {

                        userList.value = list
                    }

                }
                it.close()
            }
            observeRefreshing.postValue(false)
        }

    }


}