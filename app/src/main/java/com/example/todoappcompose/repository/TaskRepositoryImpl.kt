package com.example.todoappcompose.repository

import com.example.todoappcompose.dao.TaskDao
import com.example.todoappcompose.entity.TaskEntity
import com.example.todoappcompose.enums.TaskFilter
import com.example.todoappcompose.enums.TaskStatus
import com.example.todoappcompose.utils.CalendarUtils.getEndOfDayTimestamp
import com.example.todoappcompose.utils.CalendarUtils.getEndOfMonthTimestamp
import com.example.todoappcompose.utils.CalendarUtils.getStartOfDayTimestamp
import com.example.todoappcompose.utils.CalendarUtils.getStartOfMonthTimestamp
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) :TaskRepository{
    override suspend fun insert(taskEntity: TaskEntity) {
        taskDao.insert(taskEntity)
    }

    override suspend fun getAllTask(): Flow<List<TaskEntity>> {

        return getAllTask()
    }

    override suspend fun getDailyTasks(statusFilter: TaskFilter): Flow<List<TaskEntity>> {
        val startOfDay = getStartOfDayTimestamp()
        val endOfDay = getEndOfDayTimestamp()

        return taskDao.getTaskWithDate(startOfDay,endOfDay,statusFilter.filter)
    }

    override suspend fun getMonthlyTask(statusFilter: TaskFilter): Flow<List<TaskEntity>> {
        val startOfDay = getStartOfMonthTimestamp()
        val endOfDay = getEndOfMonthTimestamp()
        return return taskDao.getTaskWithDate(startOfDay,endOfDay,statusFilter.filter)
    }

}