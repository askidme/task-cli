package com.codergm.service

import com.codergm.model.Priority
import com.codergm.model.Status
import com.codergm.model.Task
import java.time.LocalDate
import java.util.*

class TaskService {
    private var tasks = mutableListOf<Task>()

    fun addTask(title: String, description: String, dueDate: LocalDate? = null, priority: Priority = Priority.MEDIUM): Task =
        Task( title = title, description = description, dueDate = dueDate, priority = priority).also { tasks.add(it) }

    fun listTasks(): List<Task> = tasks.toList()

    fun completeTask(id: UUID): Boolean =
        tasks.find { it.id == id }?.apply { status = Status.COMPLETED} != null

    fun deleteTask(id: UUID): Boolean = tasks.removeIf { it.id == id }

}