package com.app.providerdatabase.businesslogic.viewmodel

import androidx.lifecycle.ViewModel
import com.app.providerdatabase.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @JvmField
    @Inject
    var mApplication: MyApplication? = null


}