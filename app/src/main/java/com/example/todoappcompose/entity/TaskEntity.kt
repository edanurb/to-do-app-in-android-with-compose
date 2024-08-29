package com.example.todoappcompose.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
@Entity(tableName = tableName)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id:Int=0,

    @SerialName("title")
    val title:String="",

    @SerialName("description")
    val description:String="",

    /*@SerialName("dueDate")
    val dueDate:Long=0*/
)

const val tableName="taskEntity"