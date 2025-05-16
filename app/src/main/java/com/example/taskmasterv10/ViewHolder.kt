package com.example.taskmasterv10

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_task, parent, false)) {

    private var title: TextView = itemView.findViewById(R.id.taskTitle)
    private var check: CheckBox = itemView.findViewById(R.id.taskDone)
    private var rating: RatingBar = itemView.findViewById(R.id.taskRating)
    private var priority: Spinner = itemView.findViewById(R.id.taskPriority)

    fun bind(task: Task) {
        title.text = task.title
        check.isChecked = task.done
        rating.rating = task.rating.toFloat()
    }
}
