package com.example.todoappcompose.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoappcompose.dao.TaskDao
import com.example.todoappcompose.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 2,
    /*autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],*/
    exportSchema = true
)
abstract class AppDatabase :RoomDatabase(){
    abstract val taskDao:TaskDao
}