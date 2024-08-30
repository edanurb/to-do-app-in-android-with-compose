package com.example.todoappcompose.view.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoappcompose.R
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme

@Composable
fun TaskListPage(filter: TaskFilter,time:String){
    Column {
        Text(filter.text)
        Text(time)
    }

}

@Preview
@Composable
fun TaskList(){
    taskCard(TaskEntity(0,"deneme","deneme"))
}


@Composable
fun taskCard(taskEntity: TaskEntity){
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

