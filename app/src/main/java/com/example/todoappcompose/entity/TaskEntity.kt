package com.example.todoappcompose.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
@Entity(
    tableName = tableName,
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("projectId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id:Int=0,

    @SerialName("title")
    val title:String="",

    @SerialName("description")
    val description:String="",

    @SerialName("status")
    @ColumnInfo(defaultValue = "0")
    val status:Int=0,

    @SerialName("dueDate")
    @ColumnInfo(defaultValue = "0")
    val dueDate:Long=0,

    @SerialName("projectId")
    @ColumnInfo(defaultValue = "0")
    val projectId:Int=0
)

const val tableName="taskEntity"