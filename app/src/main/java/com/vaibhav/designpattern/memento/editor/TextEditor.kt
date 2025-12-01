package com.vaibhav.designpattern.memento.editor

/**
 * Originator: The object whose state needs to be saved.
 */
class TextEditor {
    var content: String = ""

    fun createState(): EditorState {
        return EditorState(content)
    }

    fun restore(state: EditorState) {
        content = state.content
    }
}
