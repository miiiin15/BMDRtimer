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
    private val remainSecTextView: TextView by lazy {
        findViewById(R.id.secondTextView);
    }
    private val seekBar: SeekBar by lazy {
        findViewById(R.id.seekBar);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    remainMinTextView.text = "%02d".format(progress);
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar ?:return
                    creatCountDownTimer(seekBar.progress * 60 * 1000L)
                }
            }
        )
    }

    private fun creatCountDownTimer(initialMillis: Long): CountDownTimer {
        return object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekbar(millisUntilFinished)
            }

            override fun onFinish() {
                updateRemainTime(0)
                updateSeekbar(0)
            }
        }
    }

    private fun updateRemainTime(remainMilles: Long) {
        val remainseconds = remainMilles / 1000

        remainMinTextView.text = "%02".format(remainseconds / 60)
        remainSecTextView.text = "%02".format(remainseconds % 60)

    }

    private fun updateSeekbar(remainMilles: Long) {
        seekBar.progress = (remainMilles / 1000 / 60).toInt()
    }
}