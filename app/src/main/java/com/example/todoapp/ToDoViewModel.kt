package com.example.todoapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.ApiService
import com.example.todoapp.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    var taskList = mutableStateListOf<Task>()
        private set

    var errorMessage by mutableStateOf("")
        private set

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                taskList.clear()
                taskList.addAll(apiService.getTasks())
            } catch (e: Exception) {
                errorMessage = "Failed to fetch tasks: ${e.message}"
            }
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            try {
                val newTask = apiService.addTask(Task(0, title, false))
                taskList.add(newTask)
            } catch (e: Exception) {
                errorMessage = "Failed to add task: ${e.message}"
            }
        }
    }

    fun updateTask(task: Task, newTitle: String? = null, newStatus: Boolean? = null) {
        viewModelScope.launch {
            try {
                val updatedTask = apiService.updateTask(task.id, task.copy(
                    title = newTitle ?: task.title,
                    completed = newStatus ?: task.completed
                ))
                val index = taskList.indexOf(task)
                if (index != -1) {
                    taskList[index] = updatedTask
                }
            } catch (e: Exception) {
                errorMessage = "Failed to update task: ${e.message}"
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                apiService.deleteTask(task.id)
                taskList.remove(task)
            } catch (e: Exception) {
                errorMessage = "Failed to delete task: ${e.message}"
            }
        }
    }
}