package com.example.timerview

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import java.util.concurrent.TimeUnit

class TimerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val timerTextViews = mutableListOf<TextView>()
    private var mTimer = 0
    var timer: Int
        set(value) {
            mTimer = value
            updateTimer()
        }
        get() = mTimer
    private val pieces = arrayOf("0", "0", "0", "0", "0", "0")

    private fun updateTimer() {
        if (timer == 0) return
        object : CountDownTimer(timer * 60L, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var seconds = mTimer.toLong()
                val days: Long = TimeUnit.SECONDS
                    .toDays(seconds)
                seconds -= TimeUnit.DAYS.toSeconds(days)


                val hours: Long = TimeUnit.SECONDS
                    .toHours(seconds)
                seconds -= TimeUnit.HOURS.toSeconds(hours)

                val minutes: Long = TimeUnit.SECONDS
                    .toMinutes(seconds)
                val daysStr = dateToString(days)
                val hoursStr = dateToString(hours)
                val minutesStr = dateToString(minutes)
                pieces[0] = daysStr[0].toString()
                pieces[1] = daysStr[1].toString()
                pieces[2] = hoursStr[0].toString()
                pieces[3] = hoursStr[1].toString()
                pieces[4] = minutesStr[0].toString()
                pieces[5] = minutesStr[1].toString()
                mTimer--
                updateTimerViews()
            }

            override fun onFinish() {
            }
        }.start()
    }

    private fun dateToString(date: Long): String {
        val str = date.toString()
        return if (str.length == 1) "0$str"
        else str
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        orientation = VERTICAL
        updateTimerViews()
    }

    private fun updateTimerViews() {
        if(timerTextViews.isEmpty()) {
            val container = LinearLayout(context)
            container.gravity = Gravity.CENTER_HORIZONTAL
            val layoutInflater = LayoutInflater.from(context)
            for(index in pieces.indices) {
                val piece = pieces[index]
                val textView = layoutInflater.inflate(R.layout.text_view, container, false) as TextView
                textView.text = piece
                timerTextViews.add(textView)
                container.addView(textView)
                if (pieces.size - 1 == index || index % 2 == 0) continue
                val divider = layoutInflater.inflate(R.layout.divider, container, false) as TextView
                container.addView(divider)
            }
            addView(container)
            addView(layoutInflater.inflate(R.layout.time_layout, this, false))
        }
        else {
            pieces.forEachIndexed { index, piece ->
                val view = timerTextViews[index]
                if (view.text != piece) view.text = piece
            }
        }
    }


}