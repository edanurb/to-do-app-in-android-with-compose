package com.example.todoappcompose.repository

import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.enums.TaskStatus
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(taskEntity: TaskEntity)
    suspend fun getAllTask():Flow<List<TaskEntity>>

    suspend fun getDailyTasks(statusFilter: TaskFilter):Flow<List<TaskEntity>>

    suspend fun getMonthlyTask(statusFilter: TaskFilter):Flow<List<TaskEntity>>
}