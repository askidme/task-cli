package com.codergm.model

import java.time.LocalDate
import java.util.*

enum class Priority { LOW, MEDIUM, HIGH }
enum class Status { PENDING, COMPLETED }

data class Task(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val dueDate: LocalDate? = null,
    val priority: Priority = Priority.MEDIUM,
    var status: Status = Status.PENDING
)