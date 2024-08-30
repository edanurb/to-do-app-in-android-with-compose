package com.example.todoappcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import com.example.todoappcompose.viewModel.TaskViewModel


@Composable
fun labeledTextField(text:String){
    TodoAppComposeTheme {
        var textField= remember { mutableStateOf("") }

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 25.dp, vertical=25.dp)) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color(49,49,49)
            )
            TextField(
                value=textField.value,
                onValueChange = {
                    textField.value=it
                },
                singleLine=true,
                modifier= Modifier.padding(start=9.dp).size(90.dp,48.dp),
                shape= RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(244,244,244),
                    focusedContainerColor= Color(244,244,244),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),

                )
        }
    }

}
@Composable
fun titleTextField(textField: MutableState<String>){
    TodoAppComposeTheme {
        TextField(
            value=textField.value,
            onValueChange = {
                textField.value=it
            },
            singleLine=true,
            modifier = Modifier.fillMaxWidth(),
            shape= RoundedCornerShape(0),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(244,244,244),
                focusedContainerColor= Color(244,244,244),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Tittle") }
        )

    }

}
@Composable
fun descriptionTextField(textField:MutableState<String>){
    TodoAppComposeTheme {

        Column(modifier = Modifier.fillMaxWidth().padding(45.dp)){
            TextField(
                value=textField.value,
                onValueChange = {
                    textField.value=it
                },
                singleLine=true,
                shape= RoundedCornerShape(0),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(244,244,244),
                    focusedContainerColor= Color(244,244,244),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                label = { Text("Description") }
            )

        }

    }
}
@Composable
fun addButton(addTask: ()->Unit){
    Button(
        onClick = {addTask()},
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        shape = RoundedCornerShape(5.dp),
    ){
        Text("Add Task")
    }
}


@Preview(showBackground = true)
@Composable
fun AddTaskView() {
    TodoAppComposeTheme {
        val viewModel = hiltViewModel<TaskViewModel>()
        var descriptionTextField= remember { mutableStateOf("") }
        var titleTextField= remember { mutableStateOf("") }
        Column (
            modifier = Modifier.fillMaxSize().fillMaxWidth(),

        )
        {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                labeledTextField("For")
                labeledTextField("In")
            }

            titleTextField(titleTextField)
            descriptionTextField(descriptionTextField)
            addButton {

                /*viewModel.insertTask(TaskEntity(
                    title=titleTextField.value,
                    description = descriptionTextField.value
                ))*/
            }

        }
    }
}
@Composable
fun datePicker(){

}

@Preview(showBackground = true)
@Composable
fun todoTaskPreview(){
    TodoAppComposeTheme {
        var descriptionTextField= remember { mutableStateOf("") }
        var titleTextField= remember { mutableStateOf("") }
        Column (
            modifier = Modifier.fillMaxSize().fillMaxWidth(),

            )
        {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                labeledTextField("For")
                labeledTextField("In")
            }

            titleTextField(titleTextField)
            descriptionTextField(descriptionTextField)
            datePicker()
            addButton {


            }

        }
    }
}
