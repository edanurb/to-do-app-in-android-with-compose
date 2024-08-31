package com.example.todoappcompose.repository

import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun insert(projectEntity: ProjectEntity)
    suspend fun getAllTask(): Flow<List<ProjectEntity>>
}