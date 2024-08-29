package com.example.todoappcompose.repository

import com.example.todoappcompose.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(taskEntity: TaskEntity)
    suspend fun getAllTask():Flow<List<TaskEntity>>
}