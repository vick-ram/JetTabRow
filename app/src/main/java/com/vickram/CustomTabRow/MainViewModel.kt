package com.vickram.CustomTabRow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val _tabIndex = tabIndex
    val tabs = listOf("Home", "Profile", "Settings")

    fun updateTabIndexBaseOnSwipe(isSwipeToTheLeft: Boolean){
        tabIndex.value = when (isSwipeToTheLeft){
            true -> Math.floorMod(tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(tabIndex.value!!.minus(1), tabs.size)
        }
    }
    fun updateTabIndex(index: Int){
        tabIndex.value = index
    }
}