package com.vaibhav.designpattern.mediator.gamesystem

interface GameMediator {
    fun notify(sender: GameObject, event: String)
}
