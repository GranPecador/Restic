package com.example.restic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

class WaiterComponent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayout (modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                constraintSet = ConstraintSet {
                    val nameRestic = createRefFor("nameRestic")
                    val text2 = createRefFor("fragment")
                    val bottomNav = createRefFor("bottomNav")

                    constrain(nameRestic) {
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    }
                    constrain(text2) {
                        //centerTo(parent)
                        top.linkTo(nameRestic.bottom, 5.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                        bottom.linkTo(bottomNav.top, margin = 0.dp)

                    }
                    //val barrier = createBottomBarrier(text1, text2)
                    constrain(bottomNav) {
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        //centerHorizontallyTo(parent)
                        //width = Dimension.preferredWrapContent.atMost(40.dp)
                    }
                }
            ) {
                NameRestaurant()
                val navController = rememberNavController()
                    NavHost(navController, startDestination = "booking") {
                        composable("booking") { Booking() }
                        composable("tables") { Tables() }
                        composable("settings") { Settings()}
                    }
                WaiterBottomNavigation(navController)
            }
        }
    }
}

@Composable
fun Booking() {
    Box(modifier = Modifier.layoutId("fragment")){
        Text("book")

    }
}

@Composable
fun Tables() {
    Box(modifier = Modifier.layoutId("fragment")) {
        Text("table")
    }
}

@Composable
fun Settings() {
    Box(modifier = Modifier.layoutId("fragment")) {
        Text("Settings")
    }
}

@Composable
fun WaiterBottomNavigation(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Бронь", "Столик", "Настройки")
    val fragmentsItem = listOf("booking", "tables", "settings")
    BottomNavigation(modifier =Modifier.layoutId("bottomNav"),
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(route = fragmentsItem[index])
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
        modifier = Modifier.fillMaxWidth().layoutId("nameRestic"),
        textAlign = TextAlign.Center,
    )
}