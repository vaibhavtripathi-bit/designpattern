package com.vaibhav.designpattern.dpscreens.mediator.good

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GoodProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val state = viewModel.uiState

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Good Implementation (Mediator Layout)", style = MaterialTheme.typography.headlineSmall)
        Text("Components are decoupled. View Model acts as Mediator.", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            
            // 1. Name Field (Reusable, Dumb)
            MyTextField(
                label = "Name", 
                value = state.name, 
                onValueChange = { viewModel.onEvent(ProfileUiEvent.NameChanged(it)) }
            )

            // 2. Email Field (Reusable, Dumb)
            MyTextField(
                label = "Email", 
                value = state.email, 
                onValueChange = { viewModel.onEvent(ProfileUiEvent.EmailChanged(it)) }
            )

            // 3. Checkbox (Reusable, Dumb - doesn't know about dog field)
            MyCheckBox(
                label = "I have a dog",
                checked = state.isDogChecked,
                onCheckedChange = { viewModel.onEvent(ProfileUiEvent.ToggleDogCheckbox) }
            )

            // 4. Dog Name Field (Visibility controlled by State from Mediator)
            if (state.isDogNameVisible) {
                MyTextField(
                    label = "Dog Name",
                    value = state.dogName,
                    onValueChange = { viewModel.onEvent(ProfileUiEvent.DogNameChanged(it)) }
                )
            }

            // 5. Submit Button (Reusable, Dumb - doesn't know validation rules)
            MyButton(
                text = "Submit Profile",
                onClick = { viewModel.onEvent(ProfileUiEvent.SubmitClicked) }
            )

            if (state.statusMessage.isNotEmpty()) {
                Text(text = state.statusMessage, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

// --- Reusable "Dumb" Components ---

@Composable
fun MyTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MyCheckBox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(label)
    }
}

@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}
