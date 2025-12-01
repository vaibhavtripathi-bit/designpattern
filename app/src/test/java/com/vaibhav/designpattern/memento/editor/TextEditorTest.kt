package com.vaibhav.designpattern.memento.editor

import org.junit.Test
import org.junit.Assert.assertEquals

class TextEditorTest {

    @Test
    fun `test Undo Mechanism`() {
        val editor = TextEditor()
        val history = History()

        // Type "a"
        editor.content = "a"
        history.push(editor.createState())

        // Type "b"
        editor.content = "ab"
        history.push(editor.createState())

        // Type "c"
        editor.content = "abc"
        
        // Undo "c" -> should be "ab"
        val state1 = history.pop()
        if (state1 != null) {
            editor.restore(state1)
        }
        assertEquals("ab", editor.content)

        // Undo "b" -> should be "a"
        val state2 = history.pop()
        if (state2 != null) {
            editor.restore(state2)
        }
        assertEquals("a", editor.content)
    }
}
