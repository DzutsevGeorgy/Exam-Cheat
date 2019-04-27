package ru.dzgeorgy.examcheat

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.button.view.*

const val MAX_ICON_PROGRESS = 0.65f

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons.apply {
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@MainActivity, CustomScrollingLayoutCallback())
            adapter = Adapter(resources.getStringArray(R.array.questions).toList())
            requestFocus()
        }

        // Enables Always-on
        setAmbientEnabled()
    }

    inner class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {

        private var progressToCenter = 0f

        override fun onLayoutFinished(child: View, parent: RecyclerView) {
            child.apply {
                val centerOffset = height.toFloat() / 2.0f / parent.height.toFloat()
                val yRelativeToCenterOffset = y / parent.height + centerOffset
                progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset)
                progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS)
                scaleX = 1 - progressToCenter
                scaleY = 1 - progressToCenter
            }
        }

    }

    inner class Adapter(private val titles: List<String>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val button = view.button
            fun bind(title: String, position: Int) {
                button.text = title
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, AnswerActivity::class.java)
                    intent.putExtra("num", position)
                    startActivity(intent)
                }
            }
        }

        override fun getItemCount() = titles.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.button, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(titles[position], position)
        }

    }

}
