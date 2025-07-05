package com.codergm.ui

import com.codergm.model.Status
import com.codergm.service.TaskService
import java.util.*

class ConsoleUI(private val taskService: TaskService) {
    fun run() {
        println("\uD83D\uDCC6 Welcome to Daily Task Tracker!")
        println("Available commands: add, list, complete, delete, exit")
        while (true) {
            print(">")
            when (readln().trim().lowercase()) {
                "add" -> addTask()
                "list" -> listTasks()
                "complete" -> completeTask()
                "delete" -> println("TODO: Delete task")
                "exit" -> {
                    println("Goodbye!")
                    return
                }

                else -> println("Unknown command. Try again.")
            }
        }
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
        println("Enter task description")
        val desc = readln()
        println("✅ Task added: ${taskService.addTask(title = title, description = desc)}")
    }

    private fun listTasks() {
        val tasks = taskService.listTasks()

        if (tasks.isEmpty()) {
            println("🗒️ No tasks found.")
            return
        }

        println("\n Your tasks")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. [${if (task.status == Status.COMPLETED) "✓" else " "}] ${task.title} - ${task.description}")
        }
    }
}
