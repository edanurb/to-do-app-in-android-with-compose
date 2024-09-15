package com.example.todoappcompose.enums

import com.example.todoappcompose.R

enum class TaskStatus(val value:Int,val text:Int) {
    NOT_STARTED(0, R.string.not_started),
    IN_PROGRESS(1,R.string.in_progress),
    FINISHED(2,R.string.finished),

}