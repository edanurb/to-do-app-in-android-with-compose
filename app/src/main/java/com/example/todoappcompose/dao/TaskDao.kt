package com.example.todoappcompose.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskEntity: TaskEntity)

    @Query("SELECT * FROM taskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskEntity WHERE status in (:status) AND dueDate BETWEEN :startOfDay AND :endOfDay ")
    fun getTaskWithDate(startOfDay: Long, endOfDay: Long,status:List<Int>): Flow<List<TaskEntity>>


}