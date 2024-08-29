package com.example.todoappcompose

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoappcompose.database.AppDatabase
import com.example.todoappcompose.repository.TaskRepository
import com.example.todoappcompose.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app:Application):AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "AppDatabase"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(database: AppDatabase):TaskRepository{
        return TaskRepositoryImpl(database.taskDao)
    }
}