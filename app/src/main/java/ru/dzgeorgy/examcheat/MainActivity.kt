package ru.dzgeorgy.examcheat

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.button.view.*

const val MAX_ICON_PROGRESS = 0.65f

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Enables Always-on
        setAmbientEnabled()
    }


}
