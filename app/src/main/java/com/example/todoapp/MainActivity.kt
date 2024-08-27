package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.constant.Route
import com.example.todoapp.screens.LoginScreen
import com.example.todoapp.screens.Settings
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?,) {
        super.onCreate(savedInstanceState)
//        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]


        setContent {
            ToDoAppTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = Route.Login, builder = {
                    composable(Route.Login) {
                        LoginScreen(nav)
                    }
                    composable(Route.Home) {
                        Home(nav)
                    }
                    composable(Route.Settings) {
                        Settings()
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
