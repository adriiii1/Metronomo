package com.example.adri.metronomo

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bpm:Long=60
        var time:Double= (bpm/60).toDouble()
        var mp:MediaPlayer = MediaPlayer.create(this,R.raw.bar1)
        var mp2:MediaPlayer=MediaPlayer.create(this,R.raw.beat3)
        var comp:Int=0

        val runnable = object : Runnable {
            override fun run() {
                if(comp==0){
                    mp.start()
                    rb1.isChecked=true
                    rb2.isChecked=false
                    rb3.isChecked=false
                    rb4.isChecked=false
                }else if(comp==1){
                    mp.start()
                    rb1.isChecked=false
                    rb2.isChecked=true
                    rb3.isChecked=false
                    rb4.isChecked=false
                }else if(comp==2){
                    mp.start()
                    rb1.isChecked=false
                    rb2.isChecked=false
                    rb3.isChecked=true
                    rb4.isChecked=false
                }else{
                    mp2.start()
                    rb1.isChecked=false
                    rb2.isChecked=false
                    rb3.isChecked=false
                    rb4.isChecked=true
                }
                comp++
                if(comp>3){
                    comp=0
                }
                handler.postDelayed(this, bpm)
            }
        }

        btPlay.setOnClickListener {
            if(Integer.parseInt(txtBPM.text.toString())>0){
                bpm=calcMills()
            }else{
                bpm=60
            }
            handler.post(runnable)
        }

        btStop.setOnClickListener {
            handler.removeCallbacks(runnable)
            comp=0
            rb1.isChecked=false
            rb2.isChecked=false
            rb3.isChecked=false
            rb4.isChecked=false
        }
    }

    private fun calcMills():Long {
        var bpm = txtBPM.text.toString()
        var count: Int = Integer.parseInt(bpm)
        var mills = 1000.0 * 60.0 / count.toLong()
        return mills.toLong()
    }

    fun bpmMas(v: View){
        txtBPM.text=(Integer.parseInt(txtBPM.text.toString())+1).toString()
    }

    fun bpmMenos(v: View){
        txtBPM.text=(Integer.parseInt(txtBPM.text.toString())-1).toString()
    }
}
