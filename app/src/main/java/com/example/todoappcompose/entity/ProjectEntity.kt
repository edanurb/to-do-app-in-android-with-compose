package com.example.todoappcompose.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(
    tableName = projectTableName
)
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id:Int=0,

    @SerialName("title")
    val title:String="",

    @SerialName("description")
    val description:String="",

    )
const val projectTableName="projectEntity"

