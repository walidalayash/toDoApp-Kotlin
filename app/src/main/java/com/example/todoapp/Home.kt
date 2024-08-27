package com.example.todoapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.todoapp.constant.Route
import com.example.todoapp.model.BottomNavigtion
import com.example.todoapp.model.NavigationItem
import com.example.todoapp.model.Todo
import com.example.todoapp.screens.HomeScreen
import com.example.todoapp.screens.Profile
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Home(nav:NavController) {
//    val todoList by viewModel.todoList.observeAsState()

    val todoList = remember {

        mutableStateListOf<Todo>(
            Todo(1, "Prepare presentation for meeting",),
            Todo(2, "Finish report on project progress",),
//            Todo(3, "Schedule follow-up call with client",),
//            Todo(4, "Review and update project timeline",),
//            Todo(5, "Prepare agenda for team meeting",),
//            Todo(6, "Research new software tools for development",),
//            Todo(7, "Send feedback to team members on recent work",),
//            Todo(8, "Draft proposal for new project",),
//            Todo(9, "Attend training session on agile methodologies",),
//            Todo(10, "Plan and organize team building activity",),
//            Todo(11, "Review and finalize budget for next quarter",),
//            Todo(12, "Update documentation for software release",)
        )
    }


    val items = listOf(
        BottomNavigtion(
            "Home", Icons.Default.Home
        ),
        BottomNavigtion(
            "Profile", Icons.Default.Person
        )
    )
    var selectItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog, todoList)
    }

    val itemsNavigation = listOf(
        NavigationItem(
            title = "All",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Urgent",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            badgeCount = 45
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )











    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedNavigationItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    itemsNavigation.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedNavigationItemIndex,
                            onClick = {
                                selectedNavigationItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                                if(selectedNavigationItemIndex == 2){

                                    nav.navigate("info")
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedNavigationItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                               if(item.badgeCount != null )
                                Text(text = item.badgeCount.toString())
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)) } } },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = Color.Black
                        ),
                        title = { Text("ToDo App") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )



                },
                floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = {

                            shouldShowDialog.value = true
                        },
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
                    }
                },
                bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(label = { Text(text = item.title) },
                                selected = selectItemIndex == index,
                                onClick = { selectItemIndex = index },
                                icon = { Icon(item.icon, "") })
                        }

                    }
                }
            ) { p ->
                if (selectItemIndex == 0) {
                    HomeScreen(p, todoList)
                }
                if (selectItemIndex == 1) {
                    Profile(p)
                }

            }


        }

    }
}
