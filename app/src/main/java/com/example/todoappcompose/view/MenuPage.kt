package com.example.todoappcompose.view

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.viewModel.ProjectViewModel
import com.example.todoappcompose.viewModel.TaskViewModel

@Preview(showBackground = true)
@Composable
fun MenuPage(){

    val viewModel = hiltViewModel<ProjectViewModel>()
    viewModel.getAll()

    val projectList=viewModel.projectList.collectAsState(initial = listOf())
    //addProjectDialog()
    var isAddProjectDialogOpened= remember { mutableStateOf(false) }
    when(isAddProjectDialogOpened.value){
        true->{
            addProjectDialog(isAddProjectDialogOpened)
        }else->{

        }
    }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            item {
                addProjectItem(isAddProjectDialogOpened)
            }

            items(projectList.value){
                menuItem(it.title)
            }

        }
    }




}
@Composable
fun addProjectItem(isAddProjectDialogOpened:MutableState<Boolean>){

    Card(
        modifier = Modifier.height(180.dp).fillMaxWidth().padding(16.dp).clickable {
            isAddProjectDialogOpened.value=true
        },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {

        Column (
            modifier = Modifier.fillMaxWidth().height(180.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Icon(Icons.Default.Add,contentDescription = "add project", modifier = Modifier.size(24.dp,24.dp))
        }

    }
}
@Composable
fun menuItem(text:String){
    Card(
        modifier = Modifier.height(180.dp).padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column (
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            projectColorIcon()

            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=16.dp)
            )
            Text(
                text="5 Tasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top=16.dp),
                color = Color.Gray
            )
        }
    }


}
@Composable
@Preview
fun projectColorIcon(){
    Box(
    ){
        Box(
            modifier = Modifier.size(26.dp).clip(CircleShape).background(Color(96,116,249,25)).align(Alignment.Center)
        )
        Box(
            modifier = Modifier.size(14.dp).clip(CircleShape).background(Color(96,116,249)).align(Alignment.Center)
        )
    }

}


@Composable
fun addProjectDialog(isAddProjectDialogOpened: MutableState<Boolean>){
    Dialog(
        onDismissRequest = {
            isAddProjectDialogOpened.value=false
        },
        properties = DialogProperties(usePlatformDefaultWidth=false)


    ){
        addProjectView(isAddProjectDialogOpened)
    }
}

@Composable
fun addProjectView(isAddProjectDialogOpened: MutableState<Boolean>){
    var titleText= remember { mutableStateOf("") }
    var descriptionText= remember { mutableStateOf("") }
    val viewModel = hiltViewModel<ProjectViewModel>()

    //Log.d("deneme proje listesi","${taskList.value}")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0,0,0,50)).clickable(
                interactionSource = MutableInteractionSource(),
                indication = null) {
                isAddProjectDialogOpened.value=false

            },
        verticalArrangement = Arrangement.Center
        ) {
        Card(Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                TextField(
                    value = titleText.value,
                    onValueChange = {
                        titleText.value = it
                    },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = descriptionText.value,
                    onValueChange = {
                        descriptionText.value = it
                    },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                )
                Button(
                    onClick = {
                        viewModel.insert(
                            ProjectEntity(
                                title=titleText.value,
                                description = descriptionText.value
                            )
                        )
                        isAddProjectDialogOpened.value=false
                    },
                    modifier = Modifier.align(Alignment.End).padding(top = 16.dp)
                ) {
                    Text("Add")
                }
            }
        }
    }





}
