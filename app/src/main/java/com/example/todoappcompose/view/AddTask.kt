package com.example.todoappcompose.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import com.example.todoappcompose.viewModel.ProjectViewModel
import com.example.todoappcompose.viewModel.TaskViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date


/*@Composable
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

}*/
@Composable
fun labeledTextField(textField: MutableState<String>,label:String,isSingleLine:Boolean,lines:Int=1){
    TodoAppComposeTheme {
        TextField(
            value=textField.value,
            onValueChange = {
                textField.value=it
            },
            singleLine=isSingleLine,
            maxLines=lines,
            minLines=lines,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            shape= RoundedCornerShape(0),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(244,244,244),
                focusedContainerColor= Color(244,244,244),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            label = { Text(label) }
        )

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
@Composable

fun chooseProject(chosenProject:MutableState<ProjectEntity>,isChoseProjectOpened:MutableState<Boolean>){
    TextField(
        value=chosenProject.value.title,
        onValueChange = {

        },
        readOnly=true,
        enabled=false,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp).clickable(true) {
            Log.d("choose porject","clicked")
            isChoseProjectOpened.value=true
        },
        shape= RoundedCornerShape(0),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(244,244,244),
            focusedContainerColor= Color(244,244,244),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color(244,244,244),
            disabledIndicatorColor = Color(244,244,244),
            disabledLabelColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        label = { Text("Project") }
    )

}

@Composable
fun chooseProjectDialog(isChoseProjectOpened:MutableState<Boolean>,chosenProject: MutableState<ProjectEntity>){
    Dialog(
        onDismissRequest = {
            isChoseProjectOpened.value=false
        },
        properties = DialogProperties(usePlatformDefaultWidth=false)


    ){
        val viewModel = hiltViewModel<ProjectViewModel>()
        viewModel.getAll()

        val projectList=viewModel.projectList.collectAsState(initial = listOf())

        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "close",
                        modifier = Modifier.clickable {
                            isChoseProjectOpened.value=false
                        }
                    )
                }
            }
            LazyColumn {

                items(projectList.value){
                    projectItem(it,chosenProject.value==it){
                        chosenProject.value=it
                        isChoseProjectOpened.value=false
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun showıtem(){
    projectItem(ProjectEntity(),true){

    }
}
@Composable
fun projectItem(project:ProjectEntity,isChosen:Boolean,onDismiss: ()->Unit){

    Column (
        modifier = Modifier.fillMaxWidth().background(
            when(isChosen){
                true->MaterialTheme.colorScheme.primary
                else-> MaterialTheme.colorScheme.background
            }
        ).clickable(!isChosen) {
            onDismiss()
        }

    ) {
        Text(
            text = project.title,
            modifier = Modifier.padding(16.dp),
            color =
            when(isChosen){
                true->Color.White
                else->MaterialTheme.colorScheme.primary
            }
        )

        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

    }

}
private fun formatLongToString(time:Long):String{
    if(time==0L){
        return ""
    }
    val date=Date(time)
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerPopUp(dueDate: MutableState<Long>){
    val datePickerState = rememberDatePickerState()
    val showDatePicker= remember { mutableStateOf(false) }

    TextField(
        value= formatLongToString(dueDate.value),
        onValueChange = {

        },
        readOnly=true,
        enabled=false,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp).clickable(true) {
            Log.d("choose porject","clicked")
            showDatePicker.value=true
        },
        trailingIcon={
                     Icon(Icons.Default.DateRange,"date")
        },
        shape= RoundedCornerShape(0),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(244,244,244),
            focusedContainerColor= Color(244,244,244),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color(244,244,244),
            disabledIndicatorColor = Color(244,244,244),
            disabledLabelColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        label = { Text("Due Date") }
    )

    if(showDatePicker.value){
        Popup(
            alignment = Alignment.Center,
            onDismissRequest ={
                showDatePicker.value=false
                if(datePickerState.selectedDateMillis!=null){
                    dueDate.value=datePickerState.selectedDateMillis!!
                }
            }
        )
        {
            Box(
                modifier = Modifier.padding(20.dp).background(MaterialTheme.colorScheme.secondaryContainer)
            ){
                DatePicker(
                    state =datePickerState,
                    showModeToggle = false,
                    dateValidator = {
                        val currentTime=Date(System.currentTimeMillis())
                       true


                    }
                )
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddTaskView() {
    TodoAppComposeTheme {
        val viewModel = hiltViewModel<TaskViewModel>()
        var descriptionTextField= remember { mutableStateOf("") }
        var titleTextField= remember { mutableStateOf("") }
        var chosenProject= remember { mutableStateOf(ProjectEntity()) }
        var isChoseProjectOpened= remember { mutableStateOf(false) }
        var dueDate= remember { mutableStateOf(0L) }

        Column (
            modifier = Modifier.fillMaxSize().fillMaxWidth(),

        )
        {
            when(isChoseProjectOpened.value){
                true-> chooseProjectDialog(isChoseProjectOpened,chosenProject)
                else->{

                }
            }

            labeledTextField(titleTextField,"Title",true)
            labeledTextField(descriptionTextField,"Description",false,5)
            chooseProject(chosenProject,isChoseProjectOpened)
            DatePickerPopUp(dueDate)
            addButton {

                viewModel.insertTask(TaskEntity(
                    title=titleTextField.value,
                    description = descriptionTextField.value,
                    status = 0,
                    dueDate = dueDate.value,
                    projectId = chosenProject.value.id

                ))
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
        var chosenProject= remember { mutableStateOf(ProjectEntity()) }
        var isChoseProjectOpened= remember { mutableStateOf(false) }
        Column (
            modifier = Modifier.fillMaxSize().fillMaxWidth(),

            )
        {
            chooseProjectDialog(isChoseProjectOpened,chosenProject)

            labeledTextField(titleTextField,"Title",true)
            labeledTextField(descriptionTextField,"Description",false,5)
            chooseProject(chosenProject,isChoseProjectOpened)
            datePicker()
            addButton {


            }

        }
    }
}
