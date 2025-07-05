package com.codergm

import com.codergm.service.TaskService
import com.codergm.ui.ConsoleUI


fun main() {

    val taskService = TaskService()
    val ui = ConsoleUI(taskService)
    ui.run()
}