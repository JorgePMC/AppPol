package com.example.apppol.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppol.ui.navegation.ScreenIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val PnavigationEvents = MutableSharedFlow<NavigationEvent>()

    val navigationEvents: SharedFlow<NavigationEvent> = PnavigationEvents.asSharedFlow()

    fun navigateTo(screen: ScreenIn) {
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.NavigateTo(route = screen))
        }
    }

    /* no se va a usar, pero por ahora nomas, sas.
    fun navigateBack() {
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }
    fun navigateUp(){
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.navigateUp)
        }
    */
}