package com.vaibhav.designpattern.mediator.profile.good

class ProfileDialog : Mediator {
    // Components
    val nameField = TextField("NameField")
    val emailField = TextField("EmailField")
    val dogCheckBox = CheckBox("DogCheckBox")
    val dogNameField = TextField("DogNameField")
    val submitButton = Button("SubmitButton")

    init {
        // Register mediator with components
        nameField.registerMediator(this)
        emailField.registerMediator(this)
        dogCheckBox.registerMediator(this)
        dogNameField.registerMediator(this)
        submitButton.registerMediator(this)

        // Initial setup
        dogNameField.setVisible(false)
    }

    // Centralized Logic
    override fun notify(sender: Component, event: String) {
        if (sender == dogCheckBox && event == "check") {
            // Logic: "I have a dog" toggles dog name field
            dogNameField.setVisible(dogCheckBox.isChecked)
        } 
        else if (sender == submitButton && event == "click") {
            // Logic: Validation
            validateForm()
        }
    }

    private fun validateForm() {
        println("ProfileDialog: Validating form...")
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

    fun simulateUserInteraction() {
        println("--- Good Implementation (Mediator) User Flow ---")
        
        println("\n1. User enters name")
        nameField.text = "Jane Doe"
        
        println("\n2. User clicks submit (missing email)")
        submitButton.click()
        
        println("\n3. User enters email")
        emailField.text = "jane@example.com"
        
        println("\n4. User clicks 'I have a dog'")
        dogCheckBox.check() // Notifies mediator -> Dialog shows dogNameField
        
        println("\n5. User clicks submit (missing dog name)")
        submitButton.click()
        
        println("\n6. User enters dog name")
        dogNameField.text = "Rex"
        
        println("\n7. User clicks submit")
        submitButton.click()
    }
}
