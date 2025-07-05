package com.codergm.model

import com.codergm.util.CustomSerializers
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*

enum class Priority { LOW, MEDIUM, HIGH }
enum class Status { PENDING, COMPLETED }

@Serializable
data class Task(

    @Serializable(with = CustomSerializers.UuidSerializer::class)
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    @Serializable(with = CustomSerializers.LocalDateSerializer::class)
    val dueDate: LocalDate? = null,
    val priority: Priority = Priority.MEDIUM,
    var status: Status = Status.PENDING
)