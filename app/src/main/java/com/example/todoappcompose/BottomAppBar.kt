package com.example.todoappcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoappcompose.view.AddTaskView
import com.example.todoappcompose.view.HomePage.HomePage
import com.example.todoappcompose.view.MenuPage
import com.example.todoappcompose.view.ProfilePage
import com.example.todoappcompose.view.QuickPage

val Pink=Color(249,96,96)
val DarkBlue=Color(41,46,78)
val DarkGray=Color(142,142,147)

@Composable
fun BottomAppBarWithNavigation(navController: NavHostController) {


    Scaffold (bottomBar= {
       CustomBottomAppBar(navController)
    }){
        Column {
            NavHost(navController, startDestination = Screen.Home.route,Modifier.padding(it)/*.verticalScroll(rememberScrollState()).padding(16.dp)*/) {
                    composable(Screen.Home.route) { HomePage() }
                    composable(Screen.Menu.route) { MenuPage() }
                    composable(Screen.Quick.route){ QuickPage() }
                    composable(Screen.Profile.route) { ProfilePage() }
                    composable(Screen.AddTask.route){AddTaskView()}

      
            }
        }
    }


}
@Composable
fun CustomBottomAppBar(navController: NavHostController){


        Row(
            modifier = Modifier.fillMaxWidth().background(DarkBlue).padding(vertical = 4.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {


            NavigationItem(navController, Screen.Home, Icons.Default.CheckCircle, "Home")
            NavigationItem(navController, Screen.Menu, Icons.Default.Menu, "Menu")
            addButton(navController, Screen.AddTask, Icons.Default.Add, "addTask")
            NavigationItem(navController, Screen.Quick, Icons.Default.Done, "Quick")
            NavigationItem(navController, Screen.Profile, Icons.Default.Person, "Profile")
        }




}
@Composable
fun NavigationItem(
    navController: NavHostController,
    screen: Screen,
    icon: ImageVector,
    label: String
) {
    Box(
        modifier = Modifier.clickable {
            navController.navigate(screen.route)
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = label , tint = Color.White)
            Text( label, color = Color.White, fontSize = 12.sp)
        }

    }
}
@Composable
fun addButton(navController: NavHostController,
              screen: Screen,
              icon: ImageVector,
              label: String
) {
    Button(
        onClick ={navController.navigate(screen.route)},
        shape = CircleShape,
        modifier = Modifier.size(56.dp).offset(y=(-20).dp)
    ){
        Icon(imageVector = icon, contentDescription = label , tint = Color.White)
    }
/*

    Box(
        modifier = Modifier.offset(y=(-20).dp).clickable {
            navController.navigate(screen.route)
        }.clip(shape= CircleShape).background(color = Pink).size(56.dp).onFocusEvent {

        },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = label , tint = Color.White)
    }*/
}
@Preview(showBackground = true)
@Composable
fun BottomAppBarView(){

    val navController = rememberNavController()
    BottomAppBarWithNavigation(navController)
}