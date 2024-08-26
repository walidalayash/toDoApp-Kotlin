package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.ui.theme.constant.Route
import com.example.todoapp.ui.theme.model.Todo
import com.example.todoapp.ui.theme.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {


                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = Route.Login, builder = {
                    composable(Route.Login) {
                        LoginScreen(nav)
                    }
                    composable(Route.Home) {
                        Home()
                    }
                })


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppTheme {
//        LoginScreen()


    }
    }
