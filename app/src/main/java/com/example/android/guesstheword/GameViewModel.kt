package com.example.android.guesstheword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameViewModel(application: Application) :AndroidViewModel(application){

    // internal
    private val _score = MutableLiveData<Int>()
    private var _word = MutableLiveData<String>()

    //external
    val score: LiveData<Int>
        get() =_score
    val word:LiveData<String>
        get() =_word


    lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        _word.value=""
        _score.value=0
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
            _word.value = wordList.removeAt(0)
        }
    }
     fun onSkip() {
        _score.value= score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value= score.value?.plus(1)
        nextWord()
    }


}