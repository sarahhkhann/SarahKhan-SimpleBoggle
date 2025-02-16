package com.example.sarahkhan_boggleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), ScoreFragment.OnNewGameRequestedListener, LettersFragment.GameplayActionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.lettersfragment, LettersFragment())
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.scorefragment, ScoreFragment())
                .commit()
        }
    }

    private fun loadFragment(fragment: Fragment, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
    override fun onNewGameRequested() {
        val newGameplayFragment = LettersFragment()
        loadFragment(newGameplayFragment, R.id.lettersfragment)

        val scoreFragment = supportFragmentManager.findFragmentById(R.id.scorefragment) as? ScoreFragment
        scoreFragment?.updateScore(0)
    }

    override fun onScoreUpdated(score: Int) {
        Log.d("ScoreFragment", "Score received by onScoreUpdated: $score")
        val scoreFragment = supportFragmentManager.findFragmentById(R.id.scorefragment) as? ScoreFragment
        scoreFragment?.updateScore(score)
    }
}