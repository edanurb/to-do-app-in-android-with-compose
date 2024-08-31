package com.example.todoappcompose.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(projectEntity: ProjectEntity)

    @Query("SELECT * FROM projectEntity")
    fun getAllTasks(): Flow<List<ProjectEntity>>
}