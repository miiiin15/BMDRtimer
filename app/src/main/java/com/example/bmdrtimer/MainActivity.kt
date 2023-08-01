package com.example.bmdrtimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val remainMinTextView: TextView by lazy {
        findViewById(R.id.remainMinTextView);
    }
    private val seekBar: SeekBar by lazy {
        findViewById(R.id.seekBar);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun creatCountDownTimer(initialMillis:Long):CountDownTimer{
        return  object : CountDownTimer(initialMillis,1000L){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }
    }
}