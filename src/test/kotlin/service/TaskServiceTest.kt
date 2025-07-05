package service

import com.codergm.service.TaskService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TaskServiceTest {

    private lateinit var taskService: TaskService

    @BeforeEach
    fun setUp(){
        taskService = TaskService()
    }

    @Test
    fun `should add a task successfully`() {

        //Act
        val task = taskService.addTask("Title", "Description")

        //Assert
        val tasks = taskService.listTasks()
        assertThat(tasks).hasSize(1)
        assertThat(task.title).isEqualTo("Title")
        assertThat(task.description).isEqualTo("Description")
    }
}