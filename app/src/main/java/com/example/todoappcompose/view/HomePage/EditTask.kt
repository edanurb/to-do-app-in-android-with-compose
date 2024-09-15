package com.example.todoappcompose.view.HomePage

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutManagerCompat.ShortcutMatchFlags
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskStatus
import com.example.todoappcompose.view.DatePickerPopUp
import com.example.todoappcompose.view.addButton
import com.example.todoappcompose.view.chooseProject
import com.example.todoappcompose.view.chooseProjectDialog
import com.example.todoappcompose.view.labeledTextField
import com.example.todoappcompose.viewModel.TaskViewModel


@Composable
fun EditTask(task:TaskEntity){
    var descriptionTextField= remember { mutableStateOf(task.description) }
    var titleTextField= remember { mutableStateOf(task.title) }
    var chosenProject= remember { mutableStateOf(ProjectEntity()) }
    var isChoseProjectOpened= remember { mutableStateOf(false) }
    var dueDate= remember { mutableStateOf(task.dueDate) }
    var status= remember { mutableStateOf(TaskStatus.NOT_STARTED) }

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
        statusDropDownMenu(status)
        addButton {


        }

    }
}

@Composable
fun statusDropDownMenu(status: MutableState<TaskStatus>){
    val dropDownState= remember { mutableStateOf(false) }
    Box{
        TextField(
            value= stringResource(status.value.text),
            onValueChange = {

            },
            singleLine=true,
            maxLines=1,
            minLines=1,
            readOnly=true,
            enabled=false,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp).clickable(true) {
                Log.d("choose porject","clicked")
                dropDownState.value=true
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
            label = { Text("Status") },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown,"statusArrow")
            }
        )
        DropdownMenu(
            expanded = dropDownState.value,
            onDismissRequest = {
                dropDownState.value=false
            },
            modifier = Modifier.fillMaxWidth()
        ){
            TaskStatus.values().forEach {
                DropdownMenuItem(
                    text={
                        Text(stringResource(it.text))
                    },
                    onClick = {
                        status.value=it
                        dropDownState.value=false
                    },
                )
            }

        }
    }
}