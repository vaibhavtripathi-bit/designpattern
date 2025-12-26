package com.vaibhav.designpattern.mediator.profile.good

interface Mediator {
    fun notify(sender: Component, event: String)
}
