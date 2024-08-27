package com.example.todoapp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.example.todoapp.model.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>, todoList:
SnapshotStateList<Todo>) {
    var newToDo by remember {
        mutableStateOf("")
    }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = "Add Task") },
            text = {
                OutlinedTextField(value = newToDo,onValueChange ={
                    newToDo = it
                }, label = { Text("Task")})
            },
            confirmButton = {
                Button(
                    onClick = {
//                        todoList.add(
//                            Todo(2,newToDo),
//                            )
                        todoList.add(Todo(2,newToDo))
                        shouldShowDialog.value = false

                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}