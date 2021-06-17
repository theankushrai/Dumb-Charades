package com.example.android.guesstheword

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class GameViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 300000L
    }

    // internal
    private val _score = MutableLiveData<Int>()
    private val _word = MutableLiveData<String>()
    private val _eventGameFinished = MutableLiveData<Boolean>()


    //external
    val score: LiveData<Int>
        get() = _score
    val word: LiveData<String>
        get() = _word
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished


    lateinit var wordList: MutableList<String>


    //countdown timer

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }


    init {
        resetList()
        nextWord()
        _word.value = wordList[0]
        _score.value = 0
        _eventGameFinished.value = false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinished.value = true
            }
        }

        timer.start()

    }

    fun onGameFinishComplete() {
        _eventGameFinished.value = false
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "Fight Club",
            "Jaws",
            "Chef",
            "Harry Potter",
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
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}