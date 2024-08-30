package com.example.todoappcompose.view.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.R
import com.example.todoappcompose.Screen
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import com.example.todoappcompose.view.AddTaskView
import com.example.todoappcompose.view.ProfilePage
import com.example.todoappcompose.viewModel.TaskViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomePage(){
    val viewModel = hiltViewModel<TaskViewModel>()
    viewModel.getAllTasks()

    val taskList=viewModel.taskList.collectAsState(initial = listOf())
    var selectedTab= remember { mutableStateOf<Screen>(Screen.DailyTasks) }
    var selectedFilter= remember { mutableStateOf(TaskFilter.ALL) }


    Scaffold(
        topBar = {
            homePageTopBar(selectedTab,selectedFilter)
        }

    ) { innerPadding ->

        Column(modifier = Modifier.fillMaxWidth().padding(25.dp).padding(innerPadding)) {
            TaskListPage(selectedFilter.value,selectedTab.value.route)
        }

        /*LazyColumn(modifier = Modifier.fillMaxWidth().padding(25.dp).padding(innerPadding)) {
            items(taskList.value){task->
                taskCard(task.title,task.description)

            }
        }*/
    }


}


@Composable
fun homePageTopBar(
    selectedTabIndex:MutableState<Screen>,
    selectedFilter: MutableState<TaskFilter>){
        Column(
            modifier = Modifier.fillMaxWidth().background(color =MaterialTheme.colorScheme.primary)
        ) {
            topBarTitle(selectedFilter)
            TabRow(
                selectedTabIndex = when(selectedTabIndex.value.route){
                Screen.DailyTasks.route-> 0
                else->1
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    // Indicator for the selected tab
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[
                            when(selectedTabIndex.value.route){
                                Screen.DailyTasks.route-> 0
                                else->1
                            }
                        ]),
                        color = Color.White
                    )
                }
            ){
                Tab(
                    modifier = Modifier.padding(all = 16.dp),
                    selected = selectedTabIndex.value == Screen.DailyTasks,
                    onClick = { selectedTabIndex.value = Screen.DailyTasks}

                ){
                    Text("Today")
                }
                Tab(
                    modifier = Modifier.padding(all = 16.dp),
                    selected = selectedTabIndex.value == Screen.MonthlyTasks,
                    onClick = { selectedTabIndex.value = Screen.MonthlyTasks }

                ){
                    Text("Month")
                }

            }

        }


}

@Composable
fun topBarTitle(selectedFilter: MutableState<TaskFilter>){
    val items=listOf("Alexander", "Isabella", "Benjamin", "Sophia", "Christopher")
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text ="Work List",
            color = Color.White,
            fontSize = 20.sp
        )
        Spacer(Modifier.weight(1f))
        DropDownFilterMenu(selectedFilter)

    }

}

@Composable
fun DropDownFilterMenu(selectedFilter: MutableState<TaskFilter>){

    var dropDownState= remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.clickable {

            dropDownState.value=true
        }
    ){
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "filter" ,
            tint = Color.White,
        )

        DropdownMenu(
            expanded = dropDownState.value,
            onDismissRequest = {
                dropDownState.value=false
            }
        ){
            TaskFilter.values().forEach {
                DropdownMenuItem(
                    text = { Text(it.text)  },
                    onClick = {
                        dropDownState.value=false
                        selectedFilter.value=it
                    }
                )
            }
        }
    }
}

