package com.vaibhav.designpattern.mediator.profile.bad

class BadProfileDialog {
    // Components
    val nameField = BadTextField("NameField")
    val emailField = BadTextField("EmailField")
    
    // We can't change this dependency structure easily
    val dogNameField = BadTextField("DogNameField").apply { 
        setVisible(false) // Initially hidden
    }
    
    val dogCheckBox = BadDogCheckBox("DogCheckBox", dogNameField)
    
    val submitButton = BadSubmitButton(
        "SubmitButton", 
        nameField, 
        emailField, 
        dogCheckBox, 
        dogNameField
    )

    fun simulateUserInteraction() {
        println("--- Bad Implementation User Flow ---")
        
        println("\n1. User enters name")
        nameField.text = "John Doe"
        
        println("\n2. User clicks submit (missing email)")
        submitButton.click()
        
        println("\n3. User enters email")
        emailField.text = "john@example.com"
        
        println("\n4. User clicks 'I have a dog'")
        dogCheckBox.check() // This will directly reveal dogNameField
        
        println("\n5. User clicks submit (missing dog name)")
        submitButton.click()
        
        println("\n6. User enters dog name")
        dogNameField.text = "Fido"
        
        println("\n7. User clicks submit")
        submitButton.click()
    }
}
