package com.vaibhav.designpattern.mediator.ui

abstract class Component(protected val mediator: DialogMediator)

class TextBox(mediator: DialogMediator) : Component(mediator) {
    var text: String = ""
        private set

    fun setText(newText: String) {
        text = newText
        mediator.notify(this, "textChanged")
    }
}

class CheckBox(mediator: DialogMediator) : Component(mediator) {
    var checked: Boolean = false
        private set

    fun setChecked(isChecked: Boolean) {
        checked = isChecked
        mediator.notify(this, "checkChanged")
    }
}

class Button(mediator: DialogMediator) : Component(mediator) {
    var enabled: Boolean = false
        private set

    fun setEnabled(isEnabled: Boolean) {
        enabled = isEnabled
    }
}
