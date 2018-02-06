package com.vogella.android.colorfinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                color1.text = "$progress"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                color1.text = "${seekBar.progress}"
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                color1.text = "${seekBar.progress}"
            }
        })
        seekBar2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                color2.text = "$progress"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                color2.text = "${seekBar2.progress}"
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                color2.text = "${seekBar2.progress}"
            }
        })
        seekBar3.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            color3.text = "$progress"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                color3.text = "${seekBar3.progress}"
             }

            override fun onStopTrackingTouch(p0: SeekBar?)
            {
                color3.text = "${seekBar3.progress}"
            }
        })
    }
}
