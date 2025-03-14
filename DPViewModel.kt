package com.example.dpmap.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dpmap.DPMapApplication
import com.example.dpmap.data.DPMapRepository
import kotlinx.coroutines.launch
//import com.example.dpmap.network.DPApi
import com.example.dpmap.network.DPMap
import java.io.IOException

sealed interface DPUiState {
    data class Success(val map: DPMap) : DPUiState
    object Error : DPUiState
    object Loading : DPUiState
}

class DPViewModel(private val dpMapRepository: DPMapRepository) : ViewModel() {
    var dpUiState: DPUiState by mutableStateOf(DPUiState.Loading)
        private set

    init {
        getDPMap()
    }

    fun getDPMap() {
        viewModelScope.launch {
            dpUiState = try {
                //val listResult = DPApi.retrofitService.getMap()
                //val dpMapRepository = NetworkDPMapRepository()
                //val result = dpMapRepository.getDPMap()[0]
                DPUiState.Success(
                    dpMapRepository.getDPMap()[0]
                )
                //DPUiState.Success(listResult)
            } catch (e: IOException) {
                DPUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DPMapApplication)
                val dpMapRepository = application.container.dpMapRepository
                DPViewModel(dpMapRepository = dpMapRepository)
            }
        }
    }
}