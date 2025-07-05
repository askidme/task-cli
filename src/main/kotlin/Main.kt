package com.codergm

import com.codergm.service.FileTaskService
import com.codergm.ui.ConsoleUI


fun main() {

    val taskService = FileTaskService()
    val ui = ConsoleUI(taskService)
    ui.run()
}