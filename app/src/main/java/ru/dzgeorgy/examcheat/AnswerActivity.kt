package ru.dzgeorgy.examcheat

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        val num = intent.getIntExtra("num", 0)
        image.setImageDrawable(findImage(num))
//        scroll.apply {
//            setOnGenericMotionListener { v, event ->
//                if (event.action == MotionEvent.ACTION_SCROLL && RotaryEncoder.isFromRotaryEncoder(event)) {
//                    val delta = -RotaryEncoder.getRotaryAxisValue(event) * RotaryEncoder.getScaledScrollFactor(this@AnswerActivity)
//                    v.scrollBy(Math.round(delta), 0)
//                    return@setOnGenericMotionListener true
//                }
//                false
//            }
//            requestFocus()
//        }

        setAmbientEnabled()
    }

    private fun findImage(num: Int): Drawable? {
        return resources.getDrawable(resources.getIdentifier("screenshot_${num + 1}", "drawable", packageName))
    }

}