package ru.dzgeorgy.examcheat

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import kotlinx.android.synthetic.main.button.view.*
import kotlinx.android.synthetic.main.buttons_fragment.view.*
import kotlin.math.abs
import kotlin.math.min

const val MAX_ICON_PROGRESS = 0.65f

class ButtonsFragment : Fragment() {
    private var mode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments.getInt("info", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.buttons_fragment, container, false)
        view.buttons.apply {
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(context!!, CustomScrollingLayoutCallback())
            if (mode == 0)
                adapter = Adapter(resources.getStringArray(R.array.questions).toList())
            else
                adapter = Adapter(resources.getStringArray(R.array.practice).toList())
            requestFocus()
        }

        return view
    }

    inner class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {

        private var progressToCenter = 0f

        override fun onLayoutFinished(child: View, parent: RecyclerView) {
            child.apply {
                val centerOffset = height.toFloat() / 2.0f / parent.height.toFloat()
                val yRelativeToCenterOffset = y / parent.height + centerOffset
                progressToCenter = abs(0.5f - yRelativeToCenterOffset)
                progressToCenter = min(progressToCenter, MAX_ICON_PROGRESS)
                scaleX = 1 - progressToCenter
                scaleY = 1 - progressToCenter
            }
        }

    }

    inner class Adapter(private val titles: List<String>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val button: TextView = view.button
            fun bind(title: String, position: Int) {
                button.text = title
                button.setOnClickListener {
                    val intent = Intent(context!!, AnswerActivity::class.java)
                    intent.putExtra("num", position)
                    intent.putExtra("mode", mode)
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