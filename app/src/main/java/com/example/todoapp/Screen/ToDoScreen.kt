package com.example.todoapp.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.todoapp.ToDoViewModel
import com.example.todoapp.data.Task
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ToDoScreen(viewModel: ToDoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val tasks by remember { derivedStateOf { viewModel.taskList } }
    val errorMessage by remember { derivedStateOf { viewModel.errorMessage } }
    var editedTask by remember { mutableStateOf<Task?>(null) }
    var editText by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(tasks) { task ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).animateContentSize()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = task.completed,
                                    onCheckedChange = { isChecked ->
                                        viewModel.updateTask(task, newStatus = isChecked)
                                    }
                                )
                                Text(task.title)
                            }
                            Row {
                                Button(onClick = {
                                    editedTask = task
                                    editText = task.title
                                }) {
                                    Text("Edit")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = { viewModel.deleteTask(task) }) {
                                    Text("Delete")
                                }
                            }
                        }
                    }
                }
            }
            Button(onClick = { viewModel.addTask("New Task") }) {
                Text("Add Task")
            }
        }
    }

    if (editedTask != null) {
        AlertDialog(
            onDismissRequest = { editedTask = null },
            title = { Text("Edit Task") },
            text = {
                TextField(value = editText, onValueChange = { editText = it })
            },
            confirmButton = {
                Button(onClick = {
                    editedTask?.let {
                        viewModel.updateTask(it, newTitle = editText)
                    }
                    editedTask = null
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = { editedTask = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
