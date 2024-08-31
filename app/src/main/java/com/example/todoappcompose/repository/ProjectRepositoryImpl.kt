package com.example.todoappcompose.repository

import com.example.todoappcompose.dao.ProjectDao
import com.example.todoappcompose.dao.TaskDao
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(private val projectDao: ProjectDao) :ProjectRepository{
    override suspend fun insert(projectEntity: ProjectEntity){
        projectDao.insert(projectEntity)
    }
    override suspend fun getAllTask(): Flow<List<ProjectEntity>>{
        return projectDao.getAllTasks()
    }
}