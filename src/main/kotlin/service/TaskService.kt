package com.codergm.service

import com.codergm.model.Priority
import com.codergm.model.Task
import java.time.LocalDate
import java.util.*

interface TaskService {
    fun addTask(title: String, description: String, dueDate: LocalDate? = null, priority: Priority = Priority.MEDIUM): Task

    fun listTasks(): List<Task>

    fun completeTask(id: UUID): Boolean

    fun deleteTask(id: UUID): Boolean
}