package com.codergm.service

import com.codergm.model.Priority
import com.codergm.model.Status
import com.codergm.model.Task
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDate
import java.util.*

class FileTaskService(private val file: File = File("tasks.json")) : TaskService {

    private var tasks = mutableListOf<Task>()

    init {
        loadTasks()
    }

    override fun addTask(title: String, description: String, dueDate: LocalDate?, priority: Priority): Task =
        Task(title = title, description = description, dueDate = dueDate, priority = priority).also {
            tasks.add(it)
            saveTasks()
        }

    override fun listTasks(): List<Task> = tasks.toList()

    override fun completeTask(id: UUID): Boolean =
        tasks.find { it.id == id }?.apply {
            status = Status.COMPLETED
            saveTasks()
        } != null

    override fun deleteTask(id: UUID): Boolean = tasks.removeIf { it.id == id }

    private fun saveTasks() {
        val json = Json.encodeToString(tasks)
        file.writeText(json)
    }

    private fun loadTasks() {
        if (file.exists()) {
            val json = file.readText()
            tasks = Json.decodeFromString(json)
        }
    }
}