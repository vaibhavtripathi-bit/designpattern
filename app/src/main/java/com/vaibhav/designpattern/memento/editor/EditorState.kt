package com.vaibhav.designpattern.memento.editor

/**
 * Memento: Stores the internal state of the Originator.
 * It is immutable.
 */
data class EditorState(val content: String)
