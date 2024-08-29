package com.example.todoappcompose.repository

import com.example.todoappcompose.dao.TaskDao
import com.example.todoappcompose.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) :TaskRepository{
    override suspend fun insert(taskEntity: TaskEntity) {
        taskDao.insert(taskEntity)
    }

    override suspend fun getAllTask(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

}