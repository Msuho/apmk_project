package com.company.benefit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // title 관련
    private var liveTitle: String? = null
    private val _liveTitle = MutableLiveData<String>()
    val title: LiveData<String> = _liveTitle

    fun changeTitle(title: String){
        liveTitle = title
        _liveTitle.value = title
    }

}