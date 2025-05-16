package com.example.taskmasterv10

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {

    private lateinit var adapter: AdaptadorRv

    private val taskList = mutableListOf(
        Task("Estudiar C++", "Alta", false, 3),
        Task("Hacer tarea de matemáticas", "Media", true, 2),
        Task("repasar materias", "Baja", false, 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = AdaptadorRv(
            taskList,
            onDelete = { position -> deleteTask(position) },
            onEdit = { position -> showTaskDialog(editing = true, position = position) }
        )
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fab.setOnClickListener {
            showTaskDialog(editing = false)
        }
    }


    private fun ListActivity.deleteTask(position: Int) {
        taskList.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show()
    }

    private fun ListActivity.showTaskDialog(editing: Boolean,position: Int = -1) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val inputTitle = dialogView.findViewById<EditText>(R.id.editTaskTitle)

        val dialog = AlertDialog.Builder(this)
            .setTitle(if (editing) "Editar Tarea" else "Nueva Tarea")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val title = inputTitle.text.toString()
                if (title.isNotEmpty()) {
                    if (editing && position != -1) {
                        taskList[position].title = title
                        adapter.notifyItemChanged(position)
                    } else {
                        val newTask = Task(title, "Media", false, 3)
                        taskList.add(newTask)
                        adapter.notifyItemInserted(taskList.size - 1)
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()

        if (editing && position != -1) {
            inputTitle.setText(taskList[position].title)
        }

        dialog.show()
    }
}

