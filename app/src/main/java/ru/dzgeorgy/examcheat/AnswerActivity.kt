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
        //image.setImageDrawable(findImage(num))
        image.apply {
            setImageDrawable(findImage(num))
            minZoom = 2f
            setZoom(2f, 0f, 0f)
        }
        println(image.maxZoom)
        setAmbientEnabled()
    }

    private fun findImage(num: Int): Drawable? = resources.getDrawable(resources.getIdentifier("screenshot_${num + 1}", "drawable", packageName))

}