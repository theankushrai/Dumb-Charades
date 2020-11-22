package com.example.android.guesstheword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class GameViewModel(application: Application) :AndroidViewModel(application){

     var word = MutableLiveData<String>()
     var score = MutableLiveData<Int>()
     lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        word.value=""
        score.value=0
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }
     fun onSkip() {
        score.value= score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        score.value= score.value?.plus(1)
        nextWord()
    }


}