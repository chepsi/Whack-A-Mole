package com.kokonetworks.theapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val highScores = MutableLiveData<ArrayList<Int>>(ArrayList())

    fun setHighScore(newScore: Int){
        val highScoreList = highScores.value!!
        highScoreList.add(newScore)
        val sortedList = highScoreList.sortedDescending()
        highScores.postValue(ArrayList(sortedList))
    }
}