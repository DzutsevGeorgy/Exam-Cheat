package ru.dzgeorgy.examcheat

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        val num = intent.getIntExtra("qst", 0)
        val mode = intent.getIntExtra("mode", 0)
        //image.setImageDrawable(findImage(num))
        println("num=$num")
        println("mofr=$mode")
        image.apply {
            setImageDrawable(findImage(num, mode))
            minZoom = 2f
            setZoom(2f, 0f, 0f)
        }
        println(image.maxZoom)
        setAmbientEnabled()
    }

    private fun findImage(num: Int, mode: Int): Drawable? {
        if (mode == 0)
            return applicationContext.getDrawable(
                resources.getIdentifier(
                    "screenshot_${num + 1}",
                    "drawable",
                    packageName
                )
            )
        else
            return applicationContext.getDrawable(
                resources.getIdentifier(
                    "screenshot_practice_${num + 1}",
                    "drawable",
                    packageName
                )
            )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fragmentManager!!.popBackStackImmediate()
    }

}