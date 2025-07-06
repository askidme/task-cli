package com.codergm.ui

import com.codergm.model.Priority
import com.codergm.model.Status
import com.codergm.service.FileTaskService
import java.time.LocalDate
import java.util.*

class ConsoleUI(private val taskService: FileTaskService) {
    fun run() {
        println("\uD83D\uDCC6 Welcome to Daily Task Tracker!")
        println("Available commands: add, list, complete, delete, exit")
        while (true) {
            print(">")
            when (readln().trim().lowercase()) {
                "add" -> addTask()
                "list" -> listTasks()
                "complete" -> completeTask()
                "delete" -> deleteTask()
                "exit" -> {
                    println("Goodbye!")
                    return
                }

                else -> println("Unknown command. Try again.")
            }
        }
    }

    private fun deleteTask() {
        if (taskService.listTasks().isEmpty()) return println("No tasks to delete")

        listTasks()
        println("Enter the task id to delete")
        runCatching { UUID.fromString(readln()) }.getOrNull()
            ?.takeIf { taskService.deleteTask(it) }
            ?.let { println("\uD83D\uDDD1\uFE0F Task deleted successfully") }
            ?: println("❌ Invalid task number.")
    }

    private fun completeTask() {
        if (taskService.listTasks().none { it.status == Status.PENDING }) return println("No tasks to complete")

        listTasks()
        println("Enter the task id to complete: ")
        runCatching { UUID.fromString(readln()) }.getOrNull()
            ?.takeIf { taskService.completeTask(it) }
            ?.let { println("✅ Task $it completed.") }
            ?: println("❌ Invalid task number.")
    }

    private fun addTask() {
        println("Enter task title:")
        val title = readln()
        println("Enter task description:")
        val desc = readln()
        println("Enter due date (YYYY-MM-DD) or press Enter to skip")
        val dueDate = readlnOrNull()?.trim()?.takeIf { it.isNotBlank() }?.let { LocalDate.parse(it) }

        println("Enter priority (LOW, MEDIUM, HIGH): ")
        val priorityInput = readlnOrNull()?.trim()?.uppercase()
        val priority = try {
            Priority.valueOf(priorityInput ?: "MEDIUM")
        } catch(e: IllegalArgumentException){
            println("Invalid priority defaulting to MEDIUM")
            Priority.MEDIUM
        }

        println("✅ Task added: ${taskService.addTask(title = title, description = desc, dueDate = dueDate, priority = priority)}")
    }

    private fun listTasks() {
        val tasks = taskService.listTasks()

        if (tasks.isEmpty()) {
            println("🗒️ No tasks found.")
            return
        }

        println("\n Your tasks")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. [${task.status}] ${task.title} - ${task.priority} (Due: ${task.dueDate ?: "N/A"})")
        }
    }
}
