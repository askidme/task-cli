package service

import com.codergm.model.Status
import com.codergm.service.MemoryTaskService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class MemoryTaskServiceTest {

    private lateinit var taskService: MemoryTaskService

    @BeforeEach
    fun setUp() {
        taskService = MemoryTaskService()
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

    @Test
    fun `should return all added tasks`() {
        //Arrange
        taskService.addTask("T1", "D1")
        taskService.addTask("T2", "D2")

        //Act
        val tasks = taskService.listTasks()

        //Assert
        assertThat(tasks).hasSize(2)
    }

    @Test
    fun `should complete a task by ID`() {

        //Arrange
        val task = taskService.addTask("Title", "Description")

        //Act
        val result = taskService.completeTask(task.id)

        //Assert
        assertThat(result).isTrue()
        assertThat(task.status).isEqualTo(Status.COMPLETED)
    }

    @Test
    fun `should not complete a task by invalid ID`() {
        //Arrange
        val id = UUID.randomUUID()

        //Act
        val result = taskService.completeTask(id)

        //Assert
        assertThat(result).isFalse()

    }

    @Test
    fun `should delete a task by ID`() {
        //Arrange
        val task = taskService.addTask("T1", "D1")

        //Act
        val deleted = taskService.deleteTask(task.id)

        //Assert
        assertThat(deleted).isTrue()
        assertThat(taskService.listTasks()).hasSize(0)
    }

    @Test
    fun `should return false when deleting nonexistent task`() {

        //Act
        val result = taskService.deleteTask(UUID.randomUUID())

        //Assert
        assertThat(result).isFalse()
    }
}