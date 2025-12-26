package com.vaibhav.designpattern.dpscreens.mediator.bad

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BadProfileScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Bad Implementation (Coupled)", style = MaterialTheme.typography.headlineSmall)
        Text("Components are tightly coupled via direct state passing.", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        BadProfileForm()
    }
}

@Composable
fun BadProfileForm() {
    // Top-level state that components are coupled to
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val isDogChecked = remember { mutableStateOf(false) }
    val dogName = remember { mutableStateOf("") }
    val statusMessage = remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // 1. Name Field
        BadTextField(label = "Name", state = name)

        // 2. Email Field
        BadTextField(label = "Email", state = email)

        // 3. Checkbox coupled to dog name visibility logic
        BadDogCheckBox(
            isChecked = isDogChecked,
            // Direct coupling: Changes here affect whether the next field is "relevant"
            // ideally handled by a controller/mediator
        )

        // 4. Dog Name Field (Directly controlled by isDogChecked state in UI)
        if (isDogChecked.value) {
            BadTextField(label = "Dog Name", state = dogName)
        }

        // 5. Submit Button coupled to ALL state for validation
        BadSubmitButton(
            name = name,
            email = email,
            isDogChecked = isDogChecked,
            dogName = dogName,
            onStatusChange = { statusMessage.value = it }
        )

        if (statusMessage.value.isNotEmpty()) {
            Text(text = statusMessage.value, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun BadTextField(label: String, state: MutableState<String>) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BadDogCheckBox(isChecked: MutableState<Boolean>) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it }
        )
        Text("I have a dog")
    }
}

@Composable
fun BadSubmitButton(
    name: MutableState<String>,
    email: MutableState<String>,
    isDogChecked: MutableState<Boolean>,
    dogName: MutableState<String>,
    onStatusChange: (String) -> Unit
) {
    Button(
        onClick = {
            // Validation Logic inside the button! bad!
            if (name.value.isEmpty()) {
                onStatusChange("Error: Name is empty")
                return@Button
            }
            if (email.value.isEmpty()) {
                onStatusChange("Error: Email is empty")
                return@Button
            }
            if (isDogChecked.value && dogName.value.isEmpty()) {
                onStatusChange("Error: Dog name is empty")
                return@Button
            }
            onStatusChange("Success: Profile Saved!")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Submit Profile")
    }
}
