package com.example.restic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class WaiterComponent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                topBar = { NameRestaurant() },
                bottomBar = { WaiterBottomNavigation(navController = navController) }
            ) {
                NavHost(navController = navController, startDestination = "booking") {
                    composable("booking") { Booking(navController) }
                    composable("tables") { Tables() }
                    composable("settings") { Settings() }
                }

            }
        }
    }
}

@Composable
fun Booking(navController: NavController ) {
        Text("book")

}

@Composable
fun Tables() {
        Text("table")
}

@Composable
fun Settings() {
        Text("Settings")
}

@Composable
fun WaiterBottomNavigation(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Бронь", "Столик", "Настройки")
    val fragmentsItem = listOf("booking", "tables", "settings")
    BottomNavigation{
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    //navController.popBackStack(navController.graph.startDestination, false)
                    if (selectedItem != index) {
                        selectedItem = index
                        navController.navigate(route = fragmentsItem[index])
                    }
                },
                alwaysShowLabels = false,
            )
        }
    }
}

@Composable
fun NameRestaurant() {
    Text(
        text = "Ресторан №1",
        fontSize = 18.sp,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}