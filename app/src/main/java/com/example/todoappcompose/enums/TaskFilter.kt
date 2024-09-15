package com.example.todoappcompose.enums

import com.example.todoappcompose.repository.TaskRepository

enum class TaskFilter (val text:String,val filter:List<Int>){
    INCOMPLETE("Incomplete Tasks", listOf(TaskStatus.NOT_STARTED.value,TaskStatus.IN_PROGRESS.value)),
    COMPLETED("Completed Tasks", listOf(TaskStatus.FINISHED.value)),
    ALL("All Tasks", listOf(TaskStatus.IN_PROGRESS.value,TaskStatus.FINISHED.value,TaskStatus.NOT_STARTED.value));


}
