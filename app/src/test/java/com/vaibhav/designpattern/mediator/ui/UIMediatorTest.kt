package com.vaibhav.designpattern.mediator.ui

import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class UIMediatorTest {

    @Test
    fun `test Submit Button State Management`() {
        val dialog = RegistrationDialog()
        val username = TextBox(dialog)
        val terms = CheckBox(dialog)
        val submit = Button(dialog)

        // Register components with Dialog
        dialog.usernameInput = username
        dialog.termsCheckBox = terms
        dialog.submitButton = submit

        // Initial state: Empty text, Unchecked -> Disabled
        assertFalse(submit.enabled)

        // 1. Enter text only -> Disabled
        username.setText("User123")
        assertFalse(submit.enabled)

        // 2. Check box only (clear text first) -> Disabled
        username.setText("")
        terms.setChecked(true)
        assertFalse(submit.enabled)

        // 3. Both -> Enabled
        username.setText("User123")
        terms.setChecked(true)
        assertTrue(submit.enabled)

        // 4. Uncheck box -> Disabled
        terms.setChecked(false)
        assertFalse(submit.enabled)
    }
}
