package com.phani.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Toast
import com.phani.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mChronometer: Chronometer
    private var isChronometerRunning: Boolean = false
    private lateinit var mBinding: ActivityMainBinding
    private var timeAtPause: Long = 0
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mChronometer = mBinding.timerChronometer

        mBinding.playBtn.setOnClickListener { startChronometerTimer() }
        mBinding.stopBtn.setOnClickListener { pauseChronometerTimer() }
        mBinding.replayBtn.setOnClickListener { resetChronometerTimer() }
    }

    private fun startChronometerTimer() {
        if (!isChronometerRunning) {
            mChronometer.base = SystemClock.elapsedRealtime() - timeAtPause
            mChronometer.start()
            isChronometerRunning = true
            Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pauseChronometerTimer() {
        if (isChronometerRunning) {
            timeAtPause = SystemClock.elapsedRealtime() - mChronometer.base
            mChronometer.stop()
            isChronometerRunning = false
            Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetChronometerTimer() {
        mChronometer.base = SystemClock.elapsedRealtime()
        timeAtPause = 0
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}