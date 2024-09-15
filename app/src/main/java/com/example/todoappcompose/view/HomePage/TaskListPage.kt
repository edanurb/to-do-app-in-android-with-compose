package com.example.todoappcompose.view.HomePage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.R
import com.example.todoappcompose.Screen
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.enums.TaskStatus
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import com.example.todoappcompose.view.AddTaskView
import com.example.todoappcompose.viewModel.TaskViewModel
import kotlin.math.roundToInt

@Composable
fun TaskListPage(filter: TaskFilter,time:String){
    val viewModel = hiltViewModel<TaskViewModel>()
    if(time== Screen.DailyTasks.route){
        viewModel.getDailyTasks(filter)
    }else {
        viewModel.getMonthlyTasks(filter)
    }

    val taskList=viewModel.taskList.collectAsState(initial = listOf())
    var task= remember { mutableStateOf(TaskEntity()) }
    var openEditTask= remember { mutableStateOf(false) }

    if(openEditTask.value){
        Popup(

        )
        {
            Card(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            ) {
                EditTask(task.value)
            }

        }
    }




    Column {

        taskList.value.forEach {
            taskCard(it){
                task.value=it
                openEditTask.value=true
            }
        }

    }

}

@Preview
@Composable
fun TaskList(){
    taskCard(TaskEntity(0,"deneme","deneme")){

    }
}



@Composable
fun swipeOptions(onClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)

    ){
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement =Arrangement.End,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Icon(
                painterResource(R.drawable.ic_edit),
                "edit task",
                modifier = Modifier.fillMaxHeight().padding(horizontal = 25.dp).clickable {
                    onClick()
                },
                tint = MaterialTheme.colorScheme.primary
                )
            Divider(
                Modifier.fillMaxHeight().width(1.dp))
            Icon(
                painterResource(R.drawable.ic_delete),
                "delete task",
                modifier = Modifier.fillMaxHeight().padding(horizontal = 25.dp),
                tint = MaterialTheme.colorScheme.primary
            )


        }
    }
}
@Composable
fun taskCard(taskEntity: TaskEntity,onClick: ()->Unit){
    var offsetX= remember { mutableStateOf(0f) }

    // Define the max offset as half of the item's width in pixels
    val maxOffset = with(LocalDensity.current) { -(140.dp).toPx() }
    TodoAppComposeTheme {


        Box(){
            swipeOptions(){
                onClick()
            }
            ElevatedCard(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onDragEnd = {
                                // Handle swipe end logic (reset or commit swipe based on offset)
                                if (offsetX.value < maxOffset / 2) {
                                    // If more than half (in negative), snap to maxOffset
                                    offsetX.value = maxOffset
                                } else {
                                    // Else, reset to 0
                                    offsetX.value = 0f
                                }
                            },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                offsetX.value = (offsetX.value +dragAmount).coerceIn(maxOffset, 0f) // Limit swipe to negative half
                            }
                        )
                    },
                shape = RoundedCornerShape(3.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        start=13.dp,
                        top = 13.dp,
                        bottom = 13.dp ,
                        end = 0.dp)
                ) {

                    Image(
                        painter =when(taskEntity.status==TaskStatus.FINISHED.value){
                            true-> painterResource(R.drawable.checked_box)
                            else->painterResource(R.drawable.uncheck_checkbox)

                        },


                        contentDescription = ""
                    )

                    Column(
                        modifier = Modifier.weight(7f).padding(start = 16.dp)
                    ) {
                        Text(
                            text = taskEntity.title,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = taskEntity.description,
                            color= Color(155,155,155),
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            Modifier.size(4.dp,21.dp).background(Color(155,155,155))
                        )
                    }

                }


            }
        }
        }

}

