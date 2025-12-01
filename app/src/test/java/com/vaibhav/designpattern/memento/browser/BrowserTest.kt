package com.vaibhav.designpattern.memento.browser

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

class BrowserTest {

    @Test
    fun `test Browser Navigation`() {
        val browser = Browser()
        val history = NavigationHistory()

        // 1. Visit Google
        browser.visit("google.com")
        // Current: google.com, Back: [], Forward: []

        // 2. Visit GitHub
        history.commit(browser.save()) // Save Google to Back
        browser.visit("github.com")
        // Current: github.com, Back: [google.com], Forward: []

        // 3. Visit StackOverflow
        history.commit(browser.save()) // Save GitHub to Back
        browser.visit("stackoverflow.com")
        // Current: stackoverflow.com, Back: [google.com, github.com], Forward: []

        // Back -> Should go to GitHub
        val state1 = history.undo(browser.save()) // Save StackOverflow to Forward
        if (state1 != null) {
            browser.restore(state1)
        }
        assertEquals("github.com", browser.currentUrl)
        // Current: github.com, Back: [google.com], Forward: [stackoverflow.com]

        // Back -> Should go to Google
        val state2 = history.undo(browser.save()) // Save GitHub to Forward
        if (state2 != null) {
            browser.restore(state2)
        }
        assertEquals("google.com", browser.currentUrl)
        // Current: google.com, Back: [], Forward: [stackoverflow.com, github.com]

        // Forward -> Should go to GitHub
        val state3 = history.redo(browser.save()) // Save Google to Back
        if (state3 != null) {
            browser.restore(state3)
        }
        assertEquals("github.com", browser.currentUrl)
        // Current: github.com, Back: [google.com], Forward: [stackoverflow.com]

        // Forward -> Should go to StackOverflow
        val state4 = history.redo(browser.save()) // Save GitHub to Back
        if (state4 != null) {
            browser.restore(state4)
        }
        assertEquals("stackoverflow.com", browser.currentUrl)
    }

    @Test
    fun `test New Visit Clears Forward Stack`() {
        val browser = Browser()
        val history = NavigationHistory()

        browser.visit("A")
        
        history.commit(browser.save())
        browser.visit("B")

        // Back to A
        val state = history.undo(browser.save())
        if (state != null) browser.restore(state)
        assertEquals("A", browser.currentUrl)

        // Visit C (Should clear B from forward stack)
        history.commit(browser.save()) // Save A to Back
        browser.visit("C")

        // Forward should be null (B is lost)
        assertNull(history.redo(browser.save()))
    }
}
