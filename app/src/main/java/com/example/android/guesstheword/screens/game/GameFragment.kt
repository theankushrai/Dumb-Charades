
package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.GameViewModel
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    //adding a viewmodel in fragment

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel:GameViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel =ViewModelProvider(this).get(GameViewModel::class.java)
       
        //set an observer for event changes

        viewModel.eventGameFinished.observe(viewLifecycleOwner,{
            if (it){
            gameFinished()
            viewModel.onGameFinishComplete()}
        })


        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        //binding viewmodelwith data
        binding.gameViewModel=viewModel
        binding.lifecycleOwner=this
        return binding.root

    }
 /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore=viewModel.score.value?:0
        val action = GameFragmentDirections.actionGameToScore(currentScore)
        findNavController(this).navigate(action)
    }


}
