package com.vaibhav.designpattern.dpscreens.mediator.good

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// State (The data the form needs)
data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val isDogChecked: Boolean = false,
    val isDogNameVisible: Boolean = false, // Controlled by Mediator logic, not just direct flag
    val dogName: String = "",
    val statusMessage: String = ""
)

// Events (Notifications from Components to Mediator)
sealed interface ProfileUiEvent {
    data class NameChanged(val value: String) : ProfileUiEvent
    data class EmailChanged(val value: String) : ProfileUiEvent
    object ToggleDogCheckbox : ProfileUiEvent
    data class DogNameChanged(val value: String) : ProfileUiEvent
    object SubmitClicked : ProfileUiEvent
}

// The Mediator (ViewModel)
class ProfileViewModel : ViewModel() {
    
    var uiState by mutableStateOf(ProfileUiState())
        private set

    // The generic 'notify' method (here called onEvent)
    fun onEvent(event: ProfileUiEvent) {
        when(event) {
            is ProfileUiEvent.NameChanged -> {
                uiState = uiState.copy(name = event.value)
            }
            is ProfileUiEvent.EmailChanged -> {
                uiState = uiState.copy(email = event.value)
            }
            is ProfileUiEvent.ToggleDogCheckbox -> {
                // Mediator Logic: Toggling checkbox affects dog name visibility
                val newChecked = !uiState.isDogChecked
                uiState = uiState.copy(
                    isDogChecked = newChecked,
                    isDogNameVisible = newChecked
                )
            }
            is ProfileUiEvent.DogNameChanged -> {
                uiState = uiState.copy(dogName = event.value)
            }
            is ProfileUiEvent.SubmitClicked -> {
                validateForm()
            }
        }
    }

    private fun validateForm() {
        val state = uiState
        if (state.name.isEmpty()) {
            uiState = state.copy(statusMessage = "Error: Name is empty")
            return
        }
        if (state.email.isEmpty()) {
            uiState = state.copy(statusMessage = "Error: Email is empty")
            return
        }
        if (state.isDogChecked && state.dogName.isEmpty()) {
            uiState = state.copy(statusMessage = "Error: Dog name is empty")
            return
        }
        uiState = state.copy(statusMessage = "Success: Profile Saved! (Mediator worked)")
    }
}
