package com.vaibhav.designpattern.mediator.profile

import com.vaibhav.designpattern.mediator.profile.bad.BadProfileDialog
import com.vaibhav.designpattern.mediator.profile.good.ProfileDialog
import org.junit.Test
import org.junit.Assert.*

class ProfileMediatorTest {

    @Test
    fun testBadImplementation_flows() {
        val dialog = BadProfileDialog()
        
        // Initial state
        assertFalse("Dog name field should be hidden initially", dialog.dogNameField.isVisible)
        
        // 1. Check the box -> Direct side effect on dogNameField
        dialog.dogCheckBox.check()
        assertTrue("Dog name field should be visible after checking box", dialog.dogNameField.isVisible)
        
        // 2. Uncheck
        dialog.dogCheckBox.check()
        assertFalse("Dog name field should be hidden after unchecking", dialog.dogNameField.isVisible)
    }

    @Test
    fun testGoodImplementation_flows() {
        val dialog = ProfileDialog()
        
        // Initial state
        assertFalse("Dog name field should be hidden initially", dialog.dogNameField.isVisible)
        
        // 1. Check the box -> Notifies Dialog -> Dialog updates dogNameField
        dialog.dogCheckBox.check()
        assertTrue("Dog name field should be visible after checking box", dialog.dogNameField.isVisible)
        
        // Verify mediator received logic essentially works same way but decoupled
        dialog.dogCheckBox.check()
        assertFalse("Dog name field should be hidden after unchecking", dialog.dogNameField.isVisible)
        
        // Test Validation Logic (Output verification is hard without mocking logger, 
        // but we can check if state *changed* if validation passed, or just trust the flow)
        // Ideally we'd have a boolean "isSaved" on the dialog to verify success.
        
        // Reset
        dialog.nameField.text = "Test User"
        dialog.emailField.text = "test@example.com"
        dialog.dogCheckBox.check() // Visible
        dialog.dogNameField.text = "Buddy"
        
        // Click submit -> Logic runs inside Dialog
        dialog.submitButton.click()
        // Here we just ensure no exception occurs and flow completes.
    }
}
