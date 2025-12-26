package com.vaibhav.designpattern.mediator.profile.bad

// Represents a generic UI component
open class Component(val name: String) {
    var isVisible: Boolean = true
        private set

    fun setVisible(visible: Boolean) {
        isVisible = visible
        println("$name: visibility set to $visible")
    }
}

// A simple text field
class BadTextField(name: String) : Component(name) {
    var text: String = ""
        set(value) {
            field = value
            println("$name: text set to '$value'")
        }
}

// A specific check box coupled to a specific text field
class BadDogCheckBox(
    name: String,
    private val dogNameField: BadTextField // Tight Coupling!
) : Component(name) {

    var isChecked: Boolean = false
        private set

    fun check() {
        isChecked = !isChecked
        println("$name: checked is now $isChecked")
        // Direct communication - hard dependency
        dogNameField.setVisible(isChecked)
    }
}

// A submit button coupled to all specific fields it needs to validate
class BadSubmitButton(
    name: String,
    private val nameField: BadTextField,
    private val emailField: BadTextField,
    private val dogCheckBox: BadDogCheckBox,
    private val dogNameField: BadTextField
) : Component(name) {

    fun click() {
        println("$name: clicked. Validating...")
        if (nameField.text.isEmpty()) {
            println("Validation Failed: Name is empty.")
            return
        }
        if (emailField.text.isEmpty()) {
            println("Validation Failed: Email is empty.")
            return
        }
        if (dogCheckBox.isChecked && dogNameField.text.isEmpty()) {
            println("Validation Failed: Dog name is empty.")
            return
        }
        println("Validation Passed: Saving profile...")
    }
}
