package com.example.dpmap.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dpmap.R

@Composable
fun DPMapApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val dpViewModel: DPViewModel = viewModel(factory = DPViewModel.Factory)
        MapScreen(
            dpUiState = dpViewModel.dpUiState,
            retryAction = dpViewModel::getDPMap
        )
    }
}