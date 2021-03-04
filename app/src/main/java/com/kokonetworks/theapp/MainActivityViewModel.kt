package com.kokonetworks.theapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var currentScore = MutableLiveData<Int>()

}