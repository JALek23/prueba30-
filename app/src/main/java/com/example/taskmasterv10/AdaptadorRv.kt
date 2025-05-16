package com.example.taskmasterv10
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import androidx.recyclerview.widget.RecyclerView

class AdaptadorRv(private val tasks: List<Task>,
                  private val onDelete: (Int) -> Unit,
                  private val onEdit: (Int) -> Unit) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val task: Task = tasks[position]
            holder.bind(task)

            holder.itemView.setOnLongClickListener {
                val options = arrayOf("Editar", "Eliminar")
                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Opciones de tarea")
                    .setItems(options) { _, which ->
                        when (which) {
                            0 -> onEdit(position)
                            1 -> onDelete(position)
                        }
                    }
                    .show()
                true
            }

        }

        override fun getItemCount(): Int = tasks.size
    }

