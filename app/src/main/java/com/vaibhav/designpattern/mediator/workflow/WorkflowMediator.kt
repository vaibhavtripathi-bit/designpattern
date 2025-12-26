package com.vaibhav.designpattern.mediator.workflow

interface WorkflowMediator {
    fun notify(sender: WorkflowStep, event: String)
}
