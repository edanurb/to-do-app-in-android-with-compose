package com.example.todoappcompose.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.enums.TaskStatus
import com.example.todoappcompose.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository):ViewModel() {

    private val _taskList= MutableStateFlow(emptyList<TaskEntity>())
    val taskList=_taskList.asStateFlow()
    fun getAllTasks(){
        viewModelScope.launch {

            taskRepository.getAllTask().collectLatest {
                _taskList.tryEmit(it)
            }
            Log.d("tasklist","${taskList}")
        }
    }
    fun getDailyTasks(statusFilter: TaskFilter){
        Log.d("getDailyTask","getting daily Task")
        viewModelScope.launch {

            taskRepository.getDailyTasks(statusFilter).collectLatest {
                _taskList.tryEmit(it)
            }
            Log.d("tasklist","${taskList}")
        }
    }
    fun getMonthlyTasks(statusFilter: TaskFilter){
        viewModelScope.launch {

            taskRepository.getMonthlyTask(statusFilter).collectLatest {
                _taskList.tryEmit(it)
            }
            Log.d("tasklist","${taskList}")
        }
    }
    fun insertTask(taskEntity: TaskEntity){
        viewModelScope.launch {
            taskRepository.insert(taskEntity)
        }
    }
}