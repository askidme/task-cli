package com.codergm.service

import com.codergm.model.Priority
import com.codergm.model.Status
import com.codergm.model.Task
import java.time.LocalDate
import java.util.*

class MemoryTaskService : TaskService {
    private var tasks = mutableListOf<Task>()

    override fun addTask(title: String, description: String, dueDate: LocalDate?, priority: Priority): Task =
        Task(title = title, description = description, dueDate = dueDate, priority = priority).also { tasks.add(it) }

    override fun listTasks(): List<Task> = tasks.toList()

    override fun completeTask(id: UUID): Boolean =
        tasks.find { it.id == id }?.apply { status = Status.COMPLETED } != null

    override fun deleteTask(id: UUID): Boolean = tasks.removeIf { it.id == id }

}