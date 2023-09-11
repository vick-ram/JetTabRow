package com.vickram.CustomTabRow

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabScreen(viewModel: MainViewModel) {
    val tabIndex = viewModel._tabIndex.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex.value) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex.value == index,
                    onClick = { viewModel.updateTabIndex(index) },
                    icon = {
                        when (index){
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                            2 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        }
                    }
                )
            }

        }
        when (tabIndex.value){
            0 -> HomeScreen(viewModel = viewModel)
            1 -> ProfileScreen(viewModel = viewModel)
            2 -> SettingsScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(
        onDelta = { delta -> isSwipeToTheLeft = delta > 0 }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .draggable(
            state = dragState,
            orientation = Orientation.Horizontal,
            onDragStarted = {},
            onDragStopped = { viewModel.updateTabIndexBaseOnSwipe(isSwipeToTheLeft = isSwipeToTheLeft) }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Home",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(
        onDelta = { delta -> isSwipeToTheLeft = delta > 0 }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .draggable(
            state = dragState,
            orientation = Orientation.Horizontal,
            onDragStarted = {},
            onDragStopped = { viewModel.updateTabIndexBaseOnSwipe(isSwipeToTheLeft = isSwipeToTheLeft) }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Profile",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(
        onDelta = { delta -> isSwipeToTheLeft = delta > 0 }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .draggable(
            state = dragState,
            orientation = Orientation.Horizontal,
            onDragStarted = {},
            onDragStopped = { viewModel.updateTabIndexBaseOnSwipe(isSwipeToTheLeft = isSwipeToTheLeft) }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Settings",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainPreview() {
    val viewModel: MainViewModel = viewModel()
    TabScreen(viewModel = viewModel)
}