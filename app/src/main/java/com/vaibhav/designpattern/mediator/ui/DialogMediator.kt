package com.vaibhav.designpattern.mediator.ui

interface DialogMediator {
    fun notify(sender: Component, event: String)
}

class RegistrationDialog : DialogMediator {
    lateinit var usernameInput: TextBox
    lateinit var termsCheckBox: CheckBox
    lateinit var submitButton: Button

    override fun notify(sender: Component, event: String) {
        if (event == "textChanged" || event == "checkChanged") {
            validateForm()
        }
    }

    private fun validateForm() {
        val isUsernameValid = usernameInput.text.isNotEmpty()
        val isTermsAccepted = termsCheckBox.checked
        submitButton.setEnabled(isUsernameValid && isTermsAccepted)
    }
}
