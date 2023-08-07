package com.example.bmdrtimer

import android.media.SoundPool
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

    private val soundPol = SoundPool.Builder().build()

    private var currentCountDownTimer: CountDownTimer? = null

    private var tickingSoundId: Int? = null
    private var bellSoundId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        initSound()
    }


    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) updateRemainTime(progress * 60 * 1000L)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    currentCountDownTimer?.cancel()
                    currentCountDownTimer = null;
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar ?: return;

                    currentCountDownTimer = creatCountDownTimer(seekBar.progress * 60 * 1000L)
                    currentCountDownTimer?.start()

                    tickingSoundId?.let { soundId ->
                        soundPol.play(soundId, 1F, 1F, 0, -1, 1F)
                    }
                }
            }
        )
    }

    private fun initSound() {
        tickingSoundId = soundPol.load(this, R.raw.timer_ticking, 1)
        bellSoundId = soundPol.load(this, R.raw.timer_bell, 1)

    }


    private fun creatCountDownTimer(initialMillis: Long) =
        object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekbar(millisUntilFinished)
            }

            override fun onFinish() {
                updateRemainTime(0)
                updateSeekbar(0)
            }
        }


    private fun updateRemainTime(remainMillis: Long) {
        val remainSeconds = remainMillis / 1000

        remainMinTextView.text = "%02d".format(remainSeconds / 60)
        remainSecTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekbar(remainMillis: Long) {
        seekBar.progress = (remainMillis / 1000 / 60).toInt()
    }
}