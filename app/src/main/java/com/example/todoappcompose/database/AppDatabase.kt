package com.example.todoappcompose.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoappcompose.dao.ProjectDao
import com.example.todoappcompose.dao.TaskDao
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.entity.TaskEntity

@Database(
    entities = [TaskEntity::class, ProjectEntity::class],
    version = 3,
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration(from=2,to=3)
    ],
    exportSchema = true
)
abstract class AppDatabase :RoomDatabase(){
    abstract val taskDao:TaskDao
    abstract val projectDao:ProjectDao
}