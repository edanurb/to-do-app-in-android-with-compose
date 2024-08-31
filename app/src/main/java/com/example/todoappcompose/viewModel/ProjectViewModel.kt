package com.example.todoappcompose.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappcompose.entity.ProjectEntity
import com.example.todoappcompose.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ProjectViewModel @Inject constructor(private val projectRepository: ProjectRepository):ViewModel() {
    private val _projectList= MutableStateFlow(emptyList<ProjectEntity>())
    val projectList=_projectList.asStateFlow()

    fun getAll(){
        viewModelScope.launch {
            projectRepository.getAllTask().collectLatest {
                _projectList.tryEmit(it)
            }
            Log.d("tasklist","${projectList}")

        }

    }
    fun insert(projectEntity: ProjectEntity){
        viewModelScope.launch{
            projectRepository.insert(projectEntity)
        }
    }


}