package com.vaibhav.designpattern.mediator.profile.good

// Base component now knows about a generic Mediator
open class Component(val name: String, protected var mediator: Mediator? = null) {
    var isVisible: Boolean = true
        private set

    fun registerMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    fun setVisible(visible: Boolean) {
        isVisible = visible
        println("$name: visibility set to $visible")
    }
}

// Text Field that notifies on changes (if we needed it to)
class TextField(name: String) : Component(name) {
    var text: String = ""
        set(value) {
            field = value
            println("$name: text set to '$value'")
            mediator?.notify(this, "textChanged")
        }
}

// CheckBox that just notifies "check" - knows NOTHING about dog fields
class CheckBox(name: String) : Component(name) {
    var isChecked: Boolean = false
        private set

    fun check() {
        isChecked = !isChecked
        println("$name: checked is now $isChecked")
        mediator?.notify(this, "check")
    }
}

// Submit Button that just notifies "click" - knows NOTHING about validation rules
class Button(name: String) : Component(name) {
    fun click() {
        println("$name: clicked.")
        mediator?.notify(this, "click")
    }
}
