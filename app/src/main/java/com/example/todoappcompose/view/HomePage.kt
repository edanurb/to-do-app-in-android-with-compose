package com.example.todoappcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.R
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import com.example.todoappcompose.viewModel.TaskViewModel



@Preview(showBackground = true)
@Composable
fun HomePage(){
    val viewModel = hiltViewModel<TaskViewModel>()
    viewModel.getAllTasks()

    val taskList=viewModel.taskList.collectAsState(initial = listOf())

    LazyColumn(modifier = Modifier.fillMaxWidth().padding(25.dp)) {
        items(taskList.value){task->
            taskCard(task.title,task.description)

        }
    }


}

@Preview(showBackground = true)
@Composable
fun taskCard(title:String="title",description:String="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sed orci consectetur, ullamcorper quam eu, tempor urna. Pellentesque quis sollicitudin odio. Donec ultrices sit amet eros ut ullamcorper. Cras eu lectus vitae turpis mollis varius. Pellentesque id pharetra arcu, ac sagittis sem. Quisque molestie feugiat tellus, in mattis risus vestibulum ac. Sed euismod nisi at dui varius, sed malesuada leo iaculis. Etiam eu lorem felis. Aenean sit amet odio sed risus tincidunt eleifend."){
    TodoAppComposeTheme {


        ElevatedCard(

            modifier = Modifier.fillMaxWidth().height(70.dp),
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
                    painter = painterResource(R.drawable.uncheck_checkbox),
                    contentDescription = ""
                )


                Column(
                    modifier = Modifier.weight(7f).padding(start = 16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        )
                    Text(
                        text = description,
                        color=Color(155,155,155),
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